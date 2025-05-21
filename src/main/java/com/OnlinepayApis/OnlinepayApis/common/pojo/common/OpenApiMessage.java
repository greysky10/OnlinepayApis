package com.OnlinepayApis.OnlinepayApis.common.pojo.common;

/**
 * @author demoAuthor
 * @Description 报文信息
 * @Version V2.0.3
 * @Notice
 */
public class OpenApiMessage<T> {

    /****
     * 报文头
     */
    OpenApiMessageHead head;

    /****
     * 报文体
     */
    T body;

    public OpenApiMessageHead getHead() {
        return head;
    }

    public void setHead(OpenApiMessageHead head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
