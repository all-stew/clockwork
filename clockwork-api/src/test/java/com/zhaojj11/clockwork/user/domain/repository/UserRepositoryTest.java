package com.zhaojj11.clockwork.user.domain.repository;

import com.zhaojj11.clockwork.user.domain.dao.UserDao;
import com.zhaojj11.clockwork.user.domain.model.User;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import({UserRepository.class})
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    private static final Fairy fairy = Fairy.create();
    public static User userData;
    @MockBean
    private UserDao userDao;
    @Resource
    private UserRepository userRepository;

    @BeforeAll
    static void init() {
        Person person = fairy.person();

        String username = person.getUsername();
        String nickname = person.getUsername();
        String password = person.getPassword();
        String email = person.getEmail();
        String avatar = fairy.textProducer().randomString(10);

        userData = User.buildRegisterUser(username, nickname, password, email, avatar);
    }

    @Test
    void save() {
        Mockito.when(userDao.save(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean saved = userRepository.save(userData);
        assertTrue(saved);
    }

    @Test
    void remove() {
        Mockito.when(userDao.removeById(Mockito.anyLong())).thenReturn(true);
        boolean removed = userRepository.remove(6L);
        assertTrue(removed);
    }

    @Test
    void updateById() {
        Mockito.when(userDao.updateById(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean updated = userRepository.updateById(userData);
        assertNotNull(userData);
        assertTrue(updated);
    }

    @Test
    void getById() {
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(userData);
        User user = userRepository.getById(6);
        assertNotNull(user);
    }

    @Test
    void getByUsername() {
        Mockito.when(userDao.getByUsername(Mockito.anyString())).thenReturn(userData);
        User user = userRepository.getByUsername("test");
        assertNotNull(user);
    }
}