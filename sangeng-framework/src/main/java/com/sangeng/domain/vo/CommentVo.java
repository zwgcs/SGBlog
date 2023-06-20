package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 评论视图对象
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 20:28
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 根评论id
     */
    private Long rootId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 所回复的人的名字
     */
    private String toCommentUserName;
    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    /**
     * 发表评论的人的姓名
     */
    private String username;

    /**
     * 发表评论人头像
     */
    private String avatar;
    /**
     * 根评论下对应的子评论
     */
    private List<CommentVo> children;
}

