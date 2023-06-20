package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论实体类
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 20:07
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "评论实体类")
public class Comment {
    @TableId
    /**
     * 评论Id
     */
    @ApiModelProperty(notes = "评论Id")
    private Long id;
    /**
     * 评论类型（0代表文章，1代表友链）
     */
    @ApiModelProperty(notes = "评论类型（0代表文章，1代表友链）")
    private Integer type;
    /**
     * 文章Id
     */
    @ApiModelProperty(notes = "文章Id")
    private Long articleId;
    /**
     * 评论根Id
     */
    @ApiModelProperty(notes = "评论根Id")
    private Long rootId;
    /**
     * 评论内容
     */
    @ApiModelProperty(notes = "评论内容")
    private String content;
    /**
     * 所回复的目标评论的userId
     */
    @ApiModelProperty(notes = "所回复的目标评论的userId")
    private Long toCommentUserId;
    /**
     * 所回复的目标评论的Id
     */
    @ApiModelProperty(notes = "所回复的目标评论的Id")
    private Long toCommentId;
    /**
     * 创建人
     */
    @ApiModelProperty(notes = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(notes = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(notes = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(notes = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 逻辑删除（0 代表未删除，1 代表已删除）
     */
    @ApiModelProperty(notes = "文章类别是否删除(逻辑删除，0代表未删除，1代表已删除)")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer delFlag;
}
