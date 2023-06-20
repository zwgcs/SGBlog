package com.sangeng.controller;

import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.service.UploadImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理上传的相关请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/6 0:06
 * @Since version 1.0
 */
@RestController
@Api(tags = "上传图片",description = "完成图片上传接口")
public class UploadController {

    @Autowired
    private UploadImgService uploadImgService;

    @PostMapping("/upload")
    @LogPrint(BusinessName = "上传图片接口")
    @ApiOperation(value = "上传图片",notes = "上传图片到云存储器")
    @ApiImplicitParam(name = "img", value = "图片文件", required = true)
    public ResponseResult uploadImg(MultipartFile img){
        try {
            return uploadImgService.uploadImg(img);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }

}
