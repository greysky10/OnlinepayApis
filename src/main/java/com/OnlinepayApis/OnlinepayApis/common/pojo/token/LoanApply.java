package com.OnlinepayApis.OnlinepayApis.common.pojo.token;

/**
 * 支用申请 DTO
 *
 * @author zhangliang
 */

public class LoanApply {
    private String appID;
    private String reqTransTime;
    private String merchantId;
    private String version;
    private String busiMainId;
    private String bisCode;
    private String userSignatoryId;
    private String custName;
    private String personalCertNo;
    private String personalCertTypeCode;
    private String sysCode;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getReqTransTime() {
        return reqTransTime;
    }

    public void setReqTransTime(String reqTransTime) {
        this.reqTransTime = reqTransTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPersonalCertNo() {
        return personalCertNo;
    }

    public void setPersonalCertNo(String personalCertNo) {
        this.personalCertNo = personalCertNo;
    }

    public String getPersonalCertTypeCode() {
        return personalCertTypeCode;
    }

    public void setPersonalCertTypeCode(String personalCertTypeCode) {
        this.personalCertTypeCode = personalCertTypeCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
