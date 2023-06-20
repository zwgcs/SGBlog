
package com.sangeng.service.impl;

import com.sangeng.domain.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.service.UploadImgService;
import com.sangeng.utils.OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImgServiceImpl implements UploadImgService {
    @Autowired
    private OSSUtils ossUtils;


    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        String imgName = img.getOriginalFilename();
        if (!imgName.endsWith("jpg") && !imgName.endsWith("png")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        return ResponseResult.okResult(ossUtils.uploadFile(img));
    }
}
