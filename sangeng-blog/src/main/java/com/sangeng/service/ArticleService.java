package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;

/**
 * 处理文章相关业务
 * @Author SQ Email:1718048299@qq.com
 * @Since version 1.0
 */
public interface ArticleService extends IService<Article> {
    /**
     * 查询热门文章列表
     * @return
     */
    ResponseResult hotArticleList();

    /**
     * 分页查询文章列表，可按文章类别查询
     * @param pageNum 页码
     * @param pageSize 一页大小
     * @param categoryId 文章类别Id
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Integer categoryId);

    /**
     * 获取文章详情
     * @param articleId 文章Id
     * @return
     */
    ResponseResult getArticleDetail(Integer articleId);

    /**
     * 更新文章浏览量
     * @param id 文章Id
     * @return
     */
    ResponseResult updateViewCount(Long id);
}
