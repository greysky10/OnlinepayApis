package com.OnlinepayApis.OnlinepayApis.common.pojo.common;

import java.io.Serializable;

/**
 * @author demoAuthor
 * @Description 请求体
 * @Version V2.0.3
 * @Notice
 */
public class OpenApiRequest implements Serializable {

	public static final long serialVersionUID = -2158039216204850151L;
	/***
	 * 	 请求报文密文
	 */
	String request;
	/***
	 * 	请求报文签名
	 */
	String signature;
	/***
	 *	访问令牌
	 */
	String accessToken;
	/***
	 *	报文aes256加密密钥
	 */
	String encryptKey;

	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}


}
