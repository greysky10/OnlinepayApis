package com.OnlinepayApis.OnlinepayApis.common.pojo.common;

/**
 * @author demoAuthor
 * @Description 请求报文头
 * @Version V2.0.3
 * @Notice
 */
public class OpenApiMessageHead {
    /***
     * 合作方交易流水号 24位 交易唯一标识,规则日期+时间+10位数字（比如：202007151505101987563766）
     */
    private String partnerTxSriNo;

    /***
     * 接口代码
     */
    private String method;

    /***
     * 接口版本号
     */
    private String version;

    /***
     * 合作方编号
     */
    private String merchantId;
    /***
     * 伙伴应用id  21位
     */
    private String appID;
    /***
     * 报文发起时间   14位 格式：yyyyMMddHHmmss
     */
    private String reqTime;

    /***
     * 接入方式
     */
    private String accessType;

    /****
     * 预留字段
     */
    private String reserve;

    public String getPartnerTxSriNo() {
        return partnerTxSriNo;
    }

    public void setPartnerTxSriNo(String partnerTxSriNo) {
        this.partnerTxSriNo = partnerTxSriNo;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }


    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
