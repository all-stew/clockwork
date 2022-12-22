package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.RoleDao;
import com.zhaojj11.clockwork.user.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaojj11
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRepository {

    private final RoleDao roleDao;

    @NonNull
    public List<Role> listByUserId(long userId) {
        return roleDao.listByUserId(userId);
    }

    @Nullable
    public Role getById(long id) {
        return roleDao.getById(id);
    }
}
