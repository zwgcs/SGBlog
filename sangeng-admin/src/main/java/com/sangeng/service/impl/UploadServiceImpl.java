package com.sangeng.service.impl;

import com.sangeng.domain.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.service.UploadService;
import com.sangeng.utils.OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/15 13:18
 * @Description
 * @Since version 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSUtils ossUtils;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        String imgName = img.getOriginalFilename();
        if(!imgName.endsWith("jpg")&&!imgName.endsWith("png")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        return ResponseResult.okResult(ossUtils.uploadFile(img));
    }
}
