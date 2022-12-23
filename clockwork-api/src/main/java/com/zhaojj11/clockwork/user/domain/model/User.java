package com.zhaojj11.clockwork.user.domain.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zhaojj11.clockwork.common.repository.DateTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user 实体
 *
 * @author zhaojj11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "users", autoResultMap = true)
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String nickname;
    @NonNull
    private String password;
    @NonNull
    private UserStatus status;
    @NonNull
    private String email;
    @NonNull
    private String avatar;
    @TableLogic
    private boolean deleted;
    @NonNull
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @NonNull
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @NonNull
    public static User buildRegisterUser(@NonNull String username, @NonNull String nickname, @NonNull String encodedPassword, @NonNull String email, @NonNull String avatar) {
        return new User(null, username, nickname, encodedPassword, UserStatus.ENABLE, email, avatar, false, LocalDateTime.now(), LocalDateTime.now());
    }

    @Getter
    public enum UserStatus {

        /**
         * 启用状态
         */
        ENABLE(0),
        /**
         * 停用状态
         */
        DISABLE(1);

        @EnumValue
        @JsonValue
        private final int status;

        UserStatus(int status) {
            this.status = status;
        }
    }

    @Mapper
    public interface UserMapper extends BaseMapper<User> {
    }
}
