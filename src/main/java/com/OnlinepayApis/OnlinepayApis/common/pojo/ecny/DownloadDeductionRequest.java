package com.OnlinepayApis.OnlinepayApis.common.pojo.ecny;

/**
 * @author demoAuthor
 * @Description 红包发放网银扣款流水对账单文件下载 文件接口请求体
 * @Version V2.0.3
 * @Notice
 */
public class DownloadDeductionRequest {

    /**
     * 红包活动编号
     */
    private String fileId;

    /**
     * sm4key
     */
    private String sm4Key;

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setSm4Key(String sm4Key) {
        this.sm4Key = sm4Key;
    }

    public String getFileId() {
        return fileId;
    }

    public String getSm4Key() {
        return sm4Key;
    }
}