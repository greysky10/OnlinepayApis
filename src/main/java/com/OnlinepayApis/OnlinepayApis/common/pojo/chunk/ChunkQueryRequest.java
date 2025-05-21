package com.OnlinepayApis.OnlinepayApis.common.pojo.chunk;

/**
 * @author mac
 * @version 1.0
 * @date 2022/4/28 11:20 上午
 */
public class ChunkQueryRequest {
    private String accountingDate;
    private String busiMainId;

    private String reqTransTime;


    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
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
