package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.RoleDao;
import com.zhaojj11.clockwork.user.domain.model.Role;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import({RoleRepository.class})
@ExtendWith(SpringExtension.class)
class RoleRepositoryTest {

    private static final List<Role> DATA_ROLE_LIST = new ArrayList<>();
    private static Role DATA_ROLE;
    @MockBean
    private RoleDao roleDao;
    @Resource
    private RoleRepository roleRepository;

    @BeforeAll
    static void init() {
        Role role1 = Role.build("test1", "test1", 1, "test1");
        role1.setId(1L);
        DATA_ROLE = role1;
        DATA_ROLE_LIST.add(role1);

        Role role2 = Role.build("test2", "test2", 2, "test2");
        role2.setId(2L);
        DATA_ROLE_LIST.add(role2);

        Role role3 = Role.build("test3", "test3", 3, "test3");
        role3.setId(2L);
        DATA_ROLE_LIST.add(role3);
    }

    @Test
    void listByUserId() {
        Mockito.when(roleDao.listByUserId(1)).thenReturn(DATA_ROLE_LIST);
        List<Role> roles = roleRepository.listByUserId(1);
        assertEquals(3, roles.size());

        List<Role> roles2 = roleRepository.listByUserId(2);
        assertEquals(0, roles2.size());
    }

    @Test
    void getById() {
        Mockito.when(roleDao.getById(1)).thenReturn(DATA_ROLE);
        Role role = roleRepository.getById(1);
        assertNotNull(role);
        assertEquals("test1", role.getName());
    }
}