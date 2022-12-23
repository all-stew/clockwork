package com.zhaojj11.clockwork.user.domain.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaojj11.clockwork.user.domain.model.Role;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * role dao
 *
 * @author zhaojj11
 */
@Repository
public class RoleDao extends ServiceImpl<Role.RoleMapper, Role> {
    @NonNull
    public List<Role> listByUserId(long userId) {
        return getBaseMapper().listByUserId(userId);
    }

    public Role getById(long id) {
        return getBaseMapper().selectById(id);
    }
}
