package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;

    private String roleName;

    private String roleKey;

    private Long roleSort;

    private String status;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 逻辑删除（0 代表未删除，1 代表已删除）
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer delFlag;

    private String remark;
    @TableField(exist = false)
    private static final long serialVersionUID = 1545342324546464L;
}