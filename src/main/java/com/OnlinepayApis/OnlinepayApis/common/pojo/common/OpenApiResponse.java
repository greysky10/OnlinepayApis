package com.OnlinepayApis.OnlinepayApis.common.pojo.common;

/**
 * @author demoAuthor
 * @Description 响应体
 * @Version V2.0.3
 * @Notice
 */
public class OpenApiResponse {
	/***
	 * 	 响应报文密文
	 */
	String response;
	/***
	 * 	响应报文签名
	 */
	String signature;
	/***
	 *	访问令牌
	 */
	String accessToken;
	/***
	 *	报文sm4加密密钥
	 */
	String encryptKey;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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
