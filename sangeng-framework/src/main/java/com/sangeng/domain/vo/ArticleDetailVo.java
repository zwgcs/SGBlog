package com.sangeng.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章详情视图对象
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/18 20:44
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Long categoryId;
    private String categoryName;
    private String content;
    private Date createTime;
    private Long id;
    private char isComment;
    private String title;
    private Long viewCount;
}
