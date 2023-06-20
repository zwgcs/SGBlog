package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleListDto;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.ArticleTag;
import com.sangeng.domain.vo.AdminArticleTagVo;
import com.sangeng.domain.vo.AdminArticleVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.ArticleTagService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/15 19:15
 * @Since version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleTagService articleTagService;

    @Override
    public ResponseResult add(ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<Long> tags = articleDto.getTags();
        List<ArticleTag> articleTags = tags.stream().map(tid -> new ArticleTag(article.getId(), tid)).collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(articleListDto.getTitle()),Article::getTitle,articleListDto.getTitle());
        wrapper.like(StringUtils.hasText(articleListDto.getSummary()),Article::getSummary,articleListDto.getSummary());
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<Article> articles = page.getRecords();
        List<AdminArticleVo> articleVos = BeanCopyUtils.copyBean(articles, AdminArticleVo.class);
        return ResponseResult.okResult(new PageVo<>(articleVos,page.getTotal()));
    }

    @Override
    public ResponseResult info(Long articleId) {
        Article article = getById(articleId);
        if(article == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"id为："+articleId+"的文章不存在！");
        }
        AdminArticleTagVo articleVo = BeanCopyUtils.copyBean(article, AdminArticleTagVo.class);
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId,articleId);
        List<ArticleTag> articleTagList = articleTagService.list(wrapper);
        List<Long> tagIdList = articleTagList.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        articleVo.setTags(tagIdList);
        return ResponseResult.okResult(articleVo);
    }

    @Override
    public ResponseResult updateArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        updateById(article);
        List<Long> tags = articleDto.getTags();
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId,articleDto.getId());
        articleTagService.remove(wrapper);
        List<ArticleTag> articleTags = tags.stream().map(tid -> new ArticleTag(articleDto.getId(), tid)).collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long articleId) {
        removeById(articleId);
        return ResponseResult.okResult();
    }
}
