package com.sangeng.controller;

import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理类别相关的请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/18 20:44
 * @Since version 1.0
 */
@RestController
@RequestMapping("/category")
@Api(tags = "文章类别",description = "文章类别相关接口")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 类别接口
     * @return 响应对象
     */
    @GetMapping("/getCategoryList")
    @LogPrint(BusinessName = "文章分类接口")
    @ApiOperation(value = "文章分类列表",notes = "查询文章分类列表")
    public ResponseResult categoryList(){
        return categoryService.getCategoryList();
    }

}
