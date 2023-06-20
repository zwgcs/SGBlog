package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/13 11:27
 * @Description
 * @Since version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {
    private Long id;

    private Long parentId;

    private String menuName;

    private Integer orderNum;

    private String path;

    private String component;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private Date createTime;

    private String menuType;

    private Integer isFrame;

    private MenuVo[] children;
}
