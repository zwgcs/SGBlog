package com.sangeng.controller;

import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理文章相关的请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/18 20:44
 * @Since version 1.0
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关接口")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     * 热门文章接口
     * @return 响应对象
     */
    @GetMapping("/hotArticleList")
    @LogPrint(BusinessName = "热门文章接口")
    @ApiOperation(value = "热门文章列表",notes = "将文章浏览量从高到低排列")
    public ResponseResult hotArticleList(){
        return articleService.hotArticleList();
    }

    /**
     * 文章分页接口
     * @param pageNum 页码数
     * @param pageSize 页码大小
     * @param categoryId 文章类别Id
     * @return 响应对象
     */
    @GetMapping("/articleList")
    @LogPrint(BusinessName = "文章分页接口")
    @ApiOperation(value = "文章列表",notes = "获取所有文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "一页显示的文章条数", required = true),
            @ApiImplicitParam(name = "categoryId", value = "文章类别Id", required = true)
    }
    )
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Integer categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 文章详情接口
     * @param articleId 文章Id
     * @return
     */
    @GetMapping("/{id}")
    @LogPrint(BusinessName = "文章详情接口")
    @ApiOperation(value = "文章详情",notes = "获取文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章Id", required = true)
    }
    )
    public ResponseResult getArticleDetail(@PathVariable("id") Integer articleId){
        return articleService.getArticleDetail(articleId);
    }

    /**
     * 更新文章阅读量接口
     * @param id
     * @return
     */
    @PutMapping("/updateViewCount/{id}")
    @LogPrint(BusinessName = "更新文章阅读量接口")
    @ApiOperation(value = "更新阅读量",notes = "更新文章阅读量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章Id", required = true)
    }
    )
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}
