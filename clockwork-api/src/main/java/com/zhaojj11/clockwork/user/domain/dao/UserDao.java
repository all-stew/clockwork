package com.zhaojj11.clockwork.user.domain.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaojj11.clockwork.user.domain.model.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * userDao
 *
 * @author zhaojj11
 */
@Repository
public class UserDao extends ServiceImpl<User.UserMapper, User> {
    @Nullable
    public User getByUsername(@NonNull String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        return getBaseMapper().selectOne(queryWrapper);
    }
}
