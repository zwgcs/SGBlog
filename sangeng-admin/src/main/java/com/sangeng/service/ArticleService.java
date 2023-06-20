package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleListDto;
import com.sangeng.domain.entity.Article;

/**
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/15 19:14
 * @Since version 1.0
 */
public interface ArticleService extends IService<Article> {
    /**
     * 新增博文
     * @param articleDto 博文的信息
     * @return
     */
    ResponseResult add(ArticleDto articleDto);

    /**
     * 分页查询文章列表，按照文章标题和摘要进行模糊查询
     * @param  pageNum
     * @param  pageSize
     * @param  articleListDto
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    /**
     * 获取文章详情
     * @param articleId 文章Id
     * @return
     */
    ResponseResult info(Long articleId);

    /**
     * 修改文章
     * @param articleDto 修改后的文章信息
     * @return
     */
    ResponseResult updateArticle(ArticleDto articleDto);

    /**
     * 删除文章
     * @param articleId 文章Id
     * @return
     */
    ResponseResult deleteArticle(Long articleId);
}
