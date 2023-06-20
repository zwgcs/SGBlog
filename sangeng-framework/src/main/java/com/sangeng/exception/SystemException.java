package com.sangeng.exception;


import com.sangeng.enums.AppHttpCodeEnum;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 11:53
 * @Description
 * @Since version 1.0
 */
public class SystemException extends RuntimeException{
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public SystemException(AppHttpCodeEnum appHttpCodeEnum){
        super(appHttpCodeEnum.getMsg());
        this.code = appHttpCodeEnum.getCode();
        this.msg = appHttpCodeEnum.getMsg();
    }
    public SystemException(AppHttpCodeEnum appHttpCodeEnum, String msg){
        super(msg);
        this.code = appHttpCodeEnum.getCode();
        this.msg = msg;
    }
}
