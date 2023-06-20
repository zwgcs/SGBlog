package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleListDto;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/15 19:02
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PutMapping
    public ResponseResult update(@RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, ArticleListDto articleListDto){
        return articleService.articleList(pageNum,pageSize,articleListDto);
    }
    @GetMapping("/{articleId}")
    public ResponseResult articleInfo(@PathVariable Long articleId){
        return articleService.info(articleId);
    }
    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")
    public ResponseResult add(@RequestBody ArticleDto articleDto){
        return articleService.add(articleDto);
    }

    @DeleteMapping("/{articleId}")
    public ResponseResult deleteArticle(@PathVariable Long articleId){
        return articleService.deleteArticle(articleId);
    }
}
