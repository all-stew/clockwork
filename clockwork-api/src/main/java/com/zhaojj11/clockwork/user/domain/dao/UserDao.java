package com.zhaojj11.clockwork.user.domain.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaojj11.clockwork.user.domain.model.User;
import org.springframework.stereotype.Repository;

/**
 * userDao
 *
 * @author zhaojj11
 */
@Repository
public class UserDao extends ServiceImpl<User.UserMapper, User> {
}
