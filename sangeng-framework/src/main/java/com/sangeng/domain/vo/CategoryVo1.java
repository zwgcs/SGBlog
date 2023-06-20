package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/17 0:05
 * @Description
 * @Since version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo1 {
    private Long id;
    private String name;
    private String description;
    private String status;
}
