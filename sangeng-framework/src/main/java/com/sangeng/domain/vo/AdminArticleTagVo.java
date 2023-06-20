package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 12:39
 * @Description
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminArticleTagVo {
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    private String isTop;

    private String status;

    private Long viewCount;

    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;

    private List<Long> tags;
}
