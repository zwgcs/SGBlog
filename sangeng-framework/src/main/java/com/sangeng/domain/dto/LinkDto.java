package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/17 0:35
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private String Id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
}
