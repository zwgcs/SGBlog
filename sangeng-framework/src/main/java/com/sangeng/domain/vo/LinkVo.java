package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友链视图对象
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/18 22:22
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
}
