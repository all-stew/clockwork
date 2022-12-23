package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.MenuDao;
import com.zhaojj11.clockwork.user.domain.model.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * menu repository
 *
 * @author zhaojj11
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuRepository {

    private final MenuDao menuDao;

    @NonNull
    public List<Menu> listByUserId(long userId) {
        return menuDao.listByUserId(userId);
    }
}
