package io.github.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.github.config.aop.service.BaseAopService;
import io.github.dao.SysUserRoleDao;
import io.github.entity.SysUserRoleEntity;
import io.github.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 *
 * @author Created by 思伟 on 2020/6/6
 */
@Service
public class SysUserRoleServiceImpl extends BaseAopService<SysUserRoleServiceImpl, SysUserRoleDao, SysUserRoleEntity>
        implements SysUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (ObjectUtil.isEmpty(roleIdList)) {
            return;
        }
        // 先删除用户与角色关系
        baseMapper.deleteNoMapper(userId);

        // 保存用户与角色关系
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        baseMapper.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        baseMapper.deleteNoMapper(userId);
    }

    @Override
    public List<String> queryRoleNames(Long userId) {
        return baseMapper.queryRoleNames(userId);
    }
}
