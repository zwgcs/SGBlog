package com.sangeng.domain.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/5 21:53
 * @Since version 1.0
 */
@Component
@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperty {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
