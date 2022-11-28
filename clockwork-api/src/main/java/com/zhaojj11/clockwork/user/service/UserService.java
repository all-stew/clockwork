package com.zhaojj11.clockwork.user.service;

import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import org.springframework.lang.NonNull;

/**
 * UserService
 *
 * @author zhaojj11
 */
public interface UserService {
    /**
     * 保存user，id会被更新到user的主键
     *
     * @param user 用户
     * @return 是否保存成功
     */
    boolean save(@NonNull User user);

    /**
     * 删除user
     *
     * @param id user id
     * @return 返回是否删除成功
     */
    boolean remove(@NonNull Long id);

    /**
     * 根据id更新user
     *
     * @param user 待更新的数据
     * @return 是否更新成功
     */
    boolean updateById(@NonNull User user);

    /**
     * 通过id查询用户
     *
     * @param id user id
     * @return user
     */
    User getById(long id);

    /**
     * 登录,并且返回jwt
     *
     * @param dto dto
     * @return jwt
     */
    String login(LoginUserDTO dto);
}
