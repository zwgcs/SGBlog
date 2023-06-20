
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
 * 分类表对应的实体类
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/17 22:18
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "文章类别实体类")
@TableName("sg_category")
public class Category {
    @TableId
    @ApiModelProperty(notes = "文章类别Id")
    private Long id;
    @ApiModelProperty(notes = "文章类别名称")
    private String name;
    @ApiModelProperty(notes = "文章父类型")
    private Long pid;
    @ApiModelProperty(notes = "文章类别描述")
    private String description;
    @ApiModelProperty(notes = "文章类别状态，0:正常，1:禁止")
    private String status;
    @ApiModelProperty(notes = "文章类别创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @ApiModelProperty(notes = "文章类别创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "文章类别更新人")
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "文章类别更新时间")
    private Date updateTime;
    @ApiModelProperty(notes = "文章类别是否删除(逻辑删除，0代表未删除，1代表已删除)")
    private Integer delFlag;
}
