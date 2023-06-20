package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/6 0:09
 * @Description
 * @Since version 1.0
 */
public interface UploadImgService {
    /**
     * 上传图片
     * @param img 图片文件
     * @return
     */
    ResponseResult uploadImg(MultipartFile img);
}
