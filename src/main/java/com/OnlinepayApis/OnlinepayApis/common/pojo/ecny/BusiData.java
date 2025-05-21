package com.OnlinepayApis.OnlinepayApis.common.pojo.ecny;

import lombok.Data;

import java.util.List;

@Data
public class BusiData {

    private String txnCode;

    private String sourceId;

    private String reqSysId;

    private String reqTraceId;

    private String reqDate;

    private String reqReserved;

    private String mercDtTm;

    private String vendorNo;

    private String mercCode;

    private String transAmt;

    private String mercUrl;

    private String reSuccUrl;

    private String orderUrl;

    private String remark1;

    private String remark2;

    private String validTime;

    private String mercName;

    private String bizTp;

    private String orderTitle;

    private String orderCount;

    private List<OrderDetail> orderDetail;

    private String plfmNm;

    private String trxDevcInf;

    private String pyeeAcctIssrId;

    private String pyeeAcctTp;

    private String pyeeNm;

    private String terminalIp;

    private String merCustId;

    private String payEnv;

    private String wchatAppId;

    private String openId;

    private String reserveParam;

    private String tradeBizType;

    private String tradeCategoryCode;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String reserve4;

    private String reserve5;

    private String phonePayEnv;

    private String areaInfo;

    private String pyeeAcctId;
}
