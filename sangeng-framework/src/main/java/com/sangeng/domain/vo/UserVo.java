package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 22:32
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String avatar;
    private String email;
    private Long id;
    private String userName;
    private String nickName;
    private String sex;
    private String phonenumber;
    private String status;
    private Long createBy;
    private Long updateBy;
    private Date createTime;
    private Date updateTime;
}
