package com.OnlinepayApis.OnlinepayApis.common.pojo.ecny;


import lombok.Data;

@Data
public class ReceiveOrderRequest {

    /**������*/
    private String txnCode;

    /**����ID*/
    private String sourceId;

    /**����ϵͳ����*/
    private String reqSysId;

    /**���󷽽�����ˮ��*/
    private String reqTraceId;

    /**���󷽽���ʱ��*/
    private String reqDate;

    /**�����Զ����ֶ�*/
    private String reqReserved;
}
