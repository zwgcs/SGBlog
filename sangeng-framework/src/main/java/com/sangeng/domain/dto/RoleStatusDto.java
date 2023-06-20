package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 20:13
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatusDto {
    private Long roleId;
    private String status;
}
