package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable {
    private Long roleId;

    private Long menuId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1415345543225L;
}