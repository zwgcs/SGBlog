package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 8:47
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private String avatar;
    private String email;
    private Long id;
    private String nickName;
    private Character sex;
}