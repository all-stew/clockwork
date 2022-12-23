package com.zhaojj11.clockwork.user.domain.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaojj11.clockwork.user.domain.model.Menu;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * menu dao
 *
 * @author zhaojj11
 */
@Repository
public class MenuDao extends ServiceImpl<Menu.MenuMapper, Menu> {
    @NonNull
    public List<Menu> listByUserId(long userId) {
        return getBaseMapper().listByUserId(userId);
    }
}
