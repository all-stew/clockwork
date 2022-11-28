package com.zhaojj11.clockwork.user.domain.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaojj11.clockwork.common.repository.DateTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * @author zhaojj11
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "users", autoResultMap = true)
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private UserStatus status;
    private String email;
    private String avatar;
    @TableLogic
    private Boolean deleted;
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @Getter
    public enum UserStatus {
        /**
         * 启用状态
         */
        ENABLE(0);

        @EnumValue
        private final int status;

        UserStatus(int status) {
            this.status = status;
        }
    }

    @Mapper
    public interface UserMapper extends BaseMapper<User> {
    }
}
