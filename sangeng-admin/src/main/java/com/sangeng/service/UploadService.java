package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/15 13:18
 * @Description
 * @Since version 1.0
 */
public interface UploadService {
    /**
     * 上传图片到云存储器
     * @param img
     * @return
     */
    ResponseResult uploadImg(MultipartFile img);
}
