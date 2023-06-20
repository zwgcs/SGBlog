

package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.ArticleDetailVo;
import com.sangeng.domain.vo.ArticleVo;
import com.sangeng.domain.vo.HotArticleVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1L, 10L);
        page(page, wrapper);
        List<Article> records = page.getRecords();
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBean(records, HotArticleVo.class);
        Map<String, Object> map = redisCache.getCacheMap("id-viewcount-map");
        hotArticleVos.stream().forEach((hotArticleVo) -> {
            if(hotArticleVo.getViewCount().equals(0L) || hotArticleVo.getViewCount().equals(1L) || hotArticleVo.getViewCount().equals(2L)){
                map.put(hotArticleVo.getId().toString(),hotArticleVo.getViewCount().toString());
            }
            hotArticleVo.setViewCount(Long.parseLong(map.get(hotArticleVo.getId().toString()).toString()));
        });
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getIsTop);
        Page<Article> page = new Page(pageNum, pageSize);
        page(page, wrapper);
        List<Article> articles = page.getRecords();
        articles.stream().forEach(article -> {
            article.setCategoryName(((categoryService.getById(article.getCategoryId())).getName()));
        });
        List<ArticleVo> articleVoList = BeanCopyUtils.copyBean(articles, ArticleVo.class);
        Map<String, Object> map = this.redisCache.getCacheMap("id-viewcount-map");
        articleVoList.stream().forEach(articleVo -> {
            if(articleVo.getViewCount().equals(0L) || articleVo.getViewCount().equals(1L) || articleVo.getViewCount().equals(2L)){
                map.put(articleVo.getId().toString(),articleVo.getViewCount().toString());
            }
            articleVo.setViewCount(Long.parseLong(map.get(articleVo.getId().toString()).toString()));
        });
        PageVo<ArticleVo> articlePageVo = new PageVo(articleVoList, page.getTotal());
        return ResponseResult.okResult(articlePageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Integer articleId) {
        Article article = getById(articleId);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setCategoryName((categoryService.getById(article.getCategoryId())).getName());
        Long count = Long.parseLong(redisCache.getCacheMapValue("id-viewcount-map", articleId.toString()).toString());
        articleDetailVo.setViewCount(count);
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        this.redisCache.incrementCacheMapValue("id-viewcount-map", id.toString(), 1);
        return ResponseResult.okResult();
    }
}
