package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.UserDao;
import com.zhaojj11.clockwork.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author zhaojj11
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {

    private final UserDao userDao;


    public boolean save(@NonNull User user) {
        return userDao.save(user);
    }

    public boolean remove(long id) {
        return userDao.removeById(id);
    }

    public boolean updateById(@NonNull User user) {
        return userDao.updateById(user);
    }

    @Nullable
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Nullable
    public User getByUsername(@NonNull String username) {
        return userDao.getByUsername(username);
    }

}
