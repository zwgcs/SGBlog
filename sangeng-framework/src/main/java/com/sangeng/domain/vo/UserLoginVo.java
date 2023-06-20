package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 8:46
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfoVo userInfoVo;
}
