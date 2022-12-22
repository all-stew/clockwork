package com.zhaojj11.clockwork.user.domain.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zhaojj11.clockwork.common.repository.DateTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * role 实体
 *
 * @author zhaojj11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "roles", autoResultMap = true)
public class Role {
    @TableId
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String key;
    @NonNull
    private int orderNum;
    @NonNull
    private Status status;
    @NonNull
    private String remark;
    @TableLogic
    @NonNull
    private boolean deleted;
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT)
    @NonNull
    private LocalDateTime createdTime;
    @TableField(typeHandler = DateTypeHandler.class, fill = FieldFill.INSERT_UPDATE)
    @NonNull
    private LocalDateTime updatedTime;

    @NonNull
    public static Role build(@NonNull String name, @NonNull String key, int orderNum, @NonNull String remark) {
        Role role = new Role();
        role.setId(null);
        role.setName(name);
        role.setKey(key);
        role.setOrderNum(orderNum);
        role.setRemark(remark);
        role.setDeleted(false);
        role.setCreatedTime(LocalDateTime.now());
        role.setUpdatedTime(LocalDateTime.now());
        return role;
    }

    @Getter
    public enum Status {
        /**
         * 启用状态
         */
        ENABLE(0),
        /**
         * 停用
         */
        DISABLE(1);

        @EnumValue
        @JsonValue
        private final int status;

        Status(int status) {
            this.status = status;
        }
    }

    @Mapper
    public interface RoleMapper extends BaseMapper<Role> {
        /**
         * 通过userId获取该用户的所有角色
         *
         * @param userId 用户id
         * @return 角色列表
         */
        @Select("""
                SELECT DISTINCT r.* FROM roles r
                LEFT JOIN user_roles ur ON ur.role_id = r.id
                WHERE ur.user_id = #{userId}
                """)
        List<Role> listByUserId(long userId);
    }
}
