package com.OnlinepayApis.OnlinepayApis.common.exceptions;


import com.OnlinepayApis.OnlinepayApis.common.error.ErrorType;

/**
 * @author demoAuthor
 * @Description 服务端异常
 * @Version V2.0.3
 * @Notice
 */
public class ServerException extends ClientException {

    private static final long serialVersionUID = 1L;

    public ServerException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
        this.setErrorType(ErrorType.Server);
    }

}
