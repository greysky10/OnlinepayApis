package com.OnlinepayApis.OnlinepayApis.common.error;

/**
 * @author demoAuthor
 * @Description 错误码
 * @Version V2.0.3
 * @Notice
 */
public enum ErrorCodes {

    SUCCESS("000000", "交易成功"),
    SERVER_ERROR_063001("063001", "系统繁忙，请稍后再试");

    private String code;
    private String message;

    private ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
