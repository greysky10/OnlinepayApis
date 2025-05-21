package com.OnlinepayApis.OnlinepayApis.common.exceptions;


import com.OnlinepayApis.OnlinepayApis.common.error.ErrorType;

/**
 * @author demoAuthor
 * @Description 客户端异常
 * @Version V2.0.3
 * @Notice
 */
public class ClientException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMsg;

    private ErrorType errorType;

    private int httpStatusCode;

    public ClientException() {
        super();
    }

    public ClientException(Throwable cause) {
        super(cause);
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(String msg, Throwable cause) {
        super(msg, cause);
        this.errorMsg = msg;
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode + ":" + errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(String msg) {
        super(msg);
        this.errorMsg = msg;
        this.setErrorType(ErrorType.Client);
    }


    public ClientException(String errorCode, String errorMsg) {
        super(errorCode + ":" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;

    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

}
