/**
 * Created on 2021-12-21
 */
package com.OnlinepayApis.OnlinepayApis.common.pojo.token;

/**
 * 申请 token 请求报文
 * @author demo
 */
public class DrawApplyTokenRequest {

    private String appID;
    private String merchantId;
    private String reqTransTime;
    private String version;
    private String busiMainId;
    private String bisCode;
    private String userSignatoryId;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getReqTransTime() {
        return reqTransTime;
    }

    public void setReqTransTime(String reqTransTime) {
        this.reqTransTime = reqTransTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBusiMainId() {
        return busiMainId;
    }

    public void setBusiMainId(String busiMainId) {
        this.busiMainId = busiMainId;
    }

    public String getBisCode() {
        return bisCode;
    }

    public void setBisCode(String bisCode) {
        this.bisCode = bisCode;
    }

    public String getUserSignatoryId() {
        return userSignatoryId;
    }

    public void setUserSignatoryId(String userSignatoryId) {
        this.userSignatoryId = userSignatoryId;
    }
}
