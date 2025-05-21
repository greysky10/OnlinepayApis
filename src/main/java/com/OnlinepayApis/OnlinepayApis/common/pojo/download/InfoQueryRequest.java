package com.OnlinepayApis.OnlinepayApis.common.pojo.download;

/**
 * @author mac
 * @version 1.0
 * @date 2022/4/28 11:20 上午
 */
public class InfoQueryRequest {
    private String accountingDate;
    private String fileIntfcName;
    private String channelNo;

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getFileIntfcName() {
        return fileIntfcName;
    }

    public void setFileIntfcName(String fileIntfcName) {
        this.fileIntfcName = fileIntfcName;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
}
