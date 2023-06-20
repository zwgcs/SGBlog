package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 21:18
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long Id;
    private String roleName;
    private String roleKey;
    private Long roleSort;
    private String status;
    private List<String> menuIds;
    private String remark;
}
