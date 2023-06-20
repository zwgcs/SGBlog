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
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文章表对应的实体类
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/15 13:35
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "文章实体类")
@TableName("sg_article")
public class Article {
    @TableId
    @ApiModelProperty(notes = "文章Id")
    private Long id;
    @ApiModelProperty(notes = "文章标题")
    private String title;
    @ApiModelProperty(notes = "文章内容")
    private String content;
    @ApiModelProperty(notes = "文章摘要")
    private String summary;
    @ApiModelProperty(notes = "文章类别Id")
    private Long categoryId;
    @ApiModelProperty(notes = "文章缩略图")
    private String thumbnail;
    @ApiModelProperty(notes = "是否置顶")
    private String isTop;
    @ApiModelProperty(notes = "文章状态，0：已发布，1：草稿")
    private String status;
    @ApiModelProperty(notes = "文章阅读量")
    private Long viewCount;
    @ApiModelProperty(notes = "文章是否可以评论")
    private String isComment;
    @ApiModelProperty(notes = "文章创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @ApiModelProperty(notes = "文章创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @ApiModelProperty(notes = "文章更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "文章更新时间")
    private Date updateTime;
    @ApiModelProperty(notes = "文章是否删除(逻辑删除)")
    private Integer delFlag;
    @TableField(exist = false)
    private String CategoryName;
}
