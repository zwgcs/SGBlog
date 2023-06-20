package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 22:53
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo1 {
    private Long id;
    private String roleName;
    private String roleKey;
    private Long roleSort;
    private String status;
    private String remark;
    private Integer delFlag;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
}
