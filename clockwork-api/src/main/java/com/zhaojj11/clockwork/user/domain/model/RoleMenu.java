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
 * role_menu 实体
 *
 * @author zhaojj11
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "role_menus", autoResultMap = true)
public class RoleMenu {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NonNull
    private long roleId;
    @NonNull
    private long menuId;

    @NonNull
    public static RoleMenu build(long roleId, long menuId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setRoleId(menuId);
        return roleMenu;
    }

    @Mapper
    public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    }
}
