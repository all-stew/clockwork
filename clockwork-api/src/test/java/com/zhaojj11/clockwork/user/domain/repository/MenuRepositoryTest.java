package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.MenuDao;
import com.zhaojj11.clockwork.user.domain.model.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({MenuRepository.class})
@ExtendWith(SpringExtension.class)
class MenuRepositoryTest {

    private static final List<Menu> DATA_MENU_LIST = new ArrayList<>();
    @MockBean
    private MenuDao menuDao;
    @Resource
    private MenuRepository menuRepository;

    @BeforeAll
    static void init() {
        Menu menu1 = Menu.build("test1");
        menu1.setId(1L);
        DATA_MENU_LIST.add(menu1);

        Menu menu2 = Menu.build("test1");
        menu1.setId(2L);
        DATA_MENU_LIST.add(menu2);

        Menu menu3 = Menu.build("test1");
        menu1.setId(3L);
        DATA_MENU_LIST.add(menu3);
    }

    @Test
    void listByUserId() {
        Mockito.when(menuDao.listByUserId(1)).thenReturn(DATA_MENU_LIST);
        List<Menu> menus = menuRepository.listByUserId(1);
        assertEquals(3, menus.size());

        List<Menu> menus2 = menuRepository.listByUserId(2);
        assertEquals(0, menus2.size());
    }
}