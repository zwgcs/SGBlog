package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章视图对象
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/18 20:44
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    private Long id;
    private String title;
    private String summary;
    private String thumbnail;
    private Long viewCount;
    private String CategoryName;
    private Date createTime;
}
