package com.sangeng.controller;

import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理友链相关的请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/19 0:07
 * @Since version 1.0
 */
@RestController
@RequestMapping("/link")
@Api(tags = "友链",description = "友链相关接口")
public class LinkController {
    @Autowired
    private LinkService linkService;
    @GetMapping("/getAllLink")
    @LogPrint(BusinessName = "友链列表接口")
    @ApiOperation(value = "友链列表",notes = "查询友链列表")
    public ResponseResult linkList(){
        return linkService.getAllLink();
    }
}