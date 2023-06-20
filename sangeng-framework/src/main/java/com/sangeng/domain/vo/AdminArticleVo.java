package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 12:11
 * @Description
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminArticleVo {
    private Long id;
    private String title;
    private String summary;
    private String thumbnail;
    private Long viewCount;
    private String CategoryName;
    private Date createTime;
    private Long categoryId;
    private String content;
    private String isComment;
    private String isTop;
    private String status;
}
