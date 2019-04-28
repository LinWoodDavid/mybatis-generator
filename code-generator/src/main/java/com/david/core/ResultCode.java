package com.david.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NO_PERMISSIONS(403),//无权限(权限认证失败)
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500),//服务器内部错误

    //超过3位数为自定义异常
    INVALID_REQUEST(40000),//无效的请求,请求参数为空
    ACCOUNT_AUTHENTICATION_FAILED(40001);//账户认证失败(账号或密码错误)

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
