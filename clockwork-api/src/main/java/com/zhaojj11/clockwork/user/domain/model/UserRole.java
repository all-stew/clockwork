package com.zhaojj11.clockwork.user.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

/**
 * user_role 实体
 *
 * @author zhaojj11
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "user_roles", autoResultMap = true)
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NonNull
    private long userId;
    @NonNull
    private long roleId;

    @NonNull
    public static UserRole build(long userId, long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRole;
    }

    @Mapper
    public interface UserRoleMapper extends BaseMapper<UserRole> {
    }
}
