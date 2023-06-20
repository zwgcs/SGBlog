package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/12 23:57
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityInfoVo {
    private List<String> permissions;
    private List<String> roles;
    private UserInfoVo user;
}
