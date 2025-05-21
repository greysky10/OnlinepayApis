package com.OnlinepayApis.OnlinepayApis.common.pojo.ecny;

/**
 * @author demoAuthor
 * @Description 硬钱包开立 普通接口请求体
 * @Version V2.0.3
 * @Notice
 */
public class HardwareWalletOpenRequest {
    private String APDURespData;

    private String deviceName;

    private String phone;

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getCustCardId() {
        return custCardId;
    }

    public void setCustCardId(String custCardId) {
        this.custCardId = custCardId;
    }

    public String getCustCardType() {
        return custCardType;
    }

    public void setCustCardType(String custCardType) {
        this.custCardType = custCardType;
    }

    public String getCustInfoFlag() {
        return custInfoFlag;
    }

    public void setCustInfoFlag(String custInfoFlag) {
        this.custInfoFlag = custInfoFlag;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhoneNo() {
        return custPhoneNo;
    }

    public void setCustPhoneNo(String custPhoneNo) {
        this.custPhoneNo = custPhoneNo;
    }

    public String getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getPayPurpCode() {
        return payPurpCode;
    }

    public void setPayPurpCode(String payPurpCode) {
        this.payPurpCode = payPurpCode;
    }

    public String getPayPurpDesc() {
        return payPurpDesc;
    }

    public void setPayPurpDesc(String payPurpDesc) {
        this.payPurpDesc = payPurpDesc;
    }

    public String getSignAgrNo() {
        return signAgrNo;
    }

    public void setSignAgrNo(String signAgrNo) {
        this.signAgrNo = signAgrNo;
    }

    public String getSupportPartialPay() {
        return supportPartialPay;
    }

    public void setSupportPartialPay(String supportPartialPay) {
        this.supportPartialPay = supportPartialPay;
    }

    public String getTfrInWltId() {
        return tfrInWltId;
    }

    public void setTfrInWltId(String tfrInWltId) {
        this.tfrInWltId = tfrInWltId;
    }

    public String getTfrInWltInstNo() {
        return tfrInWltInstNo;
    }

    public void setTfrInWltInstNo(String tfrInWltInstNo) {
        this.tfrInWltInstNo = tfrInWltInstNo;
    }

    public String getTfrInWltNickName() {
        return tfrInWltNickName;
    }

    public void setTfrInWltNickName(String tfrInWltNickName) {
        this.tfrInWltNickName = tfrInWltNickName;
    }

    public String getTfrOutWltId() {
        return tfrOutWltId;
    }

    public void setTfrOutWltId(String tfrOutWltId) {
        this.tfrOutWltId = tfrOutWltId;
    }

    public String getTfrOutWltInstNo() {
        return tfrOutWltInstNo;
    }

    public void setTfrOutWltInstNo(String tfrOutWltInstNo) {
        this.tfrOutWltInstNo = tfrOutWltInstNo;
    }

    public String getTfrOutWltNickName() {
        return tfrOutWltNickName;
    }

    public void setTfrOutWltNickName(String tfrOutWltNickName) {
        this.tfrOutWltNickName = tfrOutWltNickName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnRmrk() {
        return txnRmrk;
    }

    public void setTxnRmrk(String txnRmrk) {
        this.txnRmrk = txnRmrk;
    }

    public String getSendInstNo() {
        return sendInstNo;
    }

    public void setSendInstNo(String sendInstNo) {
        this.sendInstNo = sendInstNo;
    }

    private String busiMainId;

    private String reqTransTime;

    /**
     * 业务代码
     */
    private String busiCode;

    /**
     * 客户证件号
     */
    private String custCardId;

    /**
     * 客户证件类型
     */
    private String custCardType;

    /**
     * 客户信息标记
     */
    private String custInfoFlag;

    /**
     * 客户姓名
     */
    private String custName;

    /**
     * 客户手机号
     */
    private String custPhoneNo;

    /**
     * 合作方编号
     */
    private String partnerNo;


    /**
     * 付款用途
     */
    private String payPurpCode;

    /**
     * 用途描述
     */
    private String payPurpDesc;

    /**
     * 代收签约协议号
     */
    private String signAgrNo;

    /**
     * 是否支持部分扣款
     * 0-不支持
     * 1-支持
     * 为空时根据合作单位结算参数确定是否支持部分扣款
     */
    private String supportPartialPay;

    /**
     * 合作方方钱包ID
     */
    private String tfrInWltId;

    /**
     * 合作方钱包所属运营机构编号
     */
    private String tfrInWltInstNo;

    /**
     * 合作方钱包名称
     */
    private String tfrInWltNickName;

    /**
     * 客户钱包id
     */
    private String tfrOutWltId;

    /**
     * 客户钱包所属运营机构
     */
    private String tfrOutWltInstNo;

    /**
     * 客户钱包昵称
     */
    private String tfrOutWltNickName;

    /**
     * 交易类型
     * 固定填TT08：
     * TT08：钱包ID转钱
     */
    private String transType;

    /**
     * 交易金额
     */
    private String txnAmt;

    /**
     * 交易备注
     */
    private String txnRmrk;

    /**
     * 交易发起机构号
     */
    private String sendInstNo;

    public String getAPDURespData() {
        return APDURespData;
    }

    public void setAPDURespData(String APDURespData) {
        this.APDURespData = APDURespData;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusiMainId() {
        return busiMainId;
    }

    public void setBusiMainId(String busiMainId) {
        this.busiMainId = busiMainId;
    }

    public String getReqTransTime() {
        return reqTransTime;
    }

    public void setReqTransTime(String reqTransTime) {
        this.reqTransTime = reqTransTime;
    }
}
