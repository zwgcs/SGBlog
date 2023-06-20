package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 22:58
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String nickName;
    private String password;
    private String phonenumber;
    private String email;
    private String sex;
    private String status;
    private String id;
    private List<String> roleIds;
}
