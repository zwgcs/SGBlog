package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 7:50
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户实体类")
@TableName("sys_user")
public class User {
    @TableField(exist = false)
    private static final long serialVersionUID = -40356785423868312L;
    @TableId
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String type;
    private String status;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
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
}
