package com.OnlinepayApis.OnlinepayApis.common.pojo.download;

public class FileDownloadRequest {
    private String fileId;
    private String fileIntfcName;
    private String sm4Key;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileIntfcName() {
        return fileIntfcName;
    }

    public void setFileIntfcName(String fileIntfcName) {
        this.fileIntfcName = fileIntfcName;
    }

    public String getSm4Key() {
        return sm4Key;
    }

    public void setSm4Key(String sm4Key) {
        this.sm4Key = sm4Key;
    }
}
