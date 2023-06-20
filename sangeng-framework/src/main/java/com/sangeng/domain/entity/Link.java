package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 友链表对应的实体类
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/18 22:10
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "友链实体类")
@TableName("sg_link")
public class Link {
    @TableId
    @ApiModelProperty(notes = "友链Id")
    private Long id;
    @ApiModelProperty(notes = "友链名称")
    private String name;
    @ApiModelProperty(notes = "友链logo")
    private String logo;
    @ApiModelProperty(notes = "友链描述")
    private String description;
    private String address;
    private String status;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Integer delFlag;
}
