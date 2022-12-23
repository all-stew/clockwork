package com.zhaojj11.clockwork.user.domain.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaojj11.clockwork.user.domain.model.UserRole;
import org.springframework.stereotype.Repository;

/**
 * user_role dao
 *
 * @author zhaojj11
 */
@Repository
public class UserRoleDao extends ServiceImpl<UserRole.UserRoleMapper, UserRole> {
}
