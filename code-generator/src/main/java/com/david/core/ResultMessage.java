package com.david.core;

/**
 * =================================
 * Created by David on 2018/12/5.
 * mail:    17610897521@163.com
 * 描述:      响应提示信息
 */

public enum ResultMessage {

    SUCCESS("SUCCESS"),//成功
    FAIL("FAIL"),//失败
    UNAUTHORIZED("UNAUTHORIZED"),//未认证（签名错误）
    NO_PERMISSIONS("NO_PERMISSIONS"),//无权限(权限认证失败)
    NOT_FOUND("NOT_FOUND"),//接口不存在
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),//服务器内部错误

    //超过3位数为自定义异常
    INVALID_REQUEST("INVALID_REQUEST"),//无效的请求,请求参数为空
    ACCOUNT_AUTHENTICATION_FAILED("账户认证失败!");   //账号或密码错误

    private final String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}
