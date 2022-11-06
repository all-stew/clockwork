package com.zhaojj11.clockwork.service.impl;

import com.zhaojj11.clockwork.domain.dao.UserDao;
import com.zhaojj11.clockwork.domain.model.User;
import com.zhaojj11.clockwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author zhaojj11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public boolean save(@NotNull User user) {
        return userDao.save(user);
    }

    @Override
    public boolean remove(@NotNull Long id) {
        return userDao.removeById(id);
    }

    @Override
    public boolean updateById(@NotNull User user) {
        return userDao.updateById(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }
}
