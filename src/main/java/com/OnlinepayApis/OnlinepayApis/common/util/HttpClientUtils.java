package com.OnlinepayApis.OnlinepayApis.common.util;

import com.OnlinepayApis.OnlinepayApis.common.exceptions.ClientException;
import com.OnlinepayApis.OnlinepayApis.common.exceptions.ServerException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author demoAuthor
 * @Description 客户端工具类
 * @Version V2.0.3
 * @Notice
 */
public class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    private static final String CHARSET_UTF_8 = "UTF-8";

    private static final int MAX_CONNECTION = 50;

    private static final long KEEPALIVE_TIME = 60000;
    /****
     * 超时时间
     */
    private static final int TIME_OUT = 50000;
    /***
     * 连接管理器
     */
    PoolingHttpClientConnectionManager connMgr;
    /***
     * 请求配置
     */
    RequestConfig requestConfig;
    /****
     *  连接超时时间 ms
     */
    int connectTimeout;
    /****
     *  socket超时时间 ms
     */
    int socketTimeout;
    /****
     * 请求超时时间 ms
     */
    int connectRequestTimemout;
    /****
     * http最大连接数
     */
    int maxConnection;
    /****
     * 每个主机最大路由数
     */
    int maxPerConnectionRoute;
    /***
     * keepalive time ms
     */
    long keepaliveTime;

    private static final Log log = LogFactory.getLog(HttpClientUtils.class);

    public static String post(String url, String jsonStr) throws ServerException {
        String res = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity;
        try {
            stringEntity = new StringEntity(jsonStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("http post json set entity error!", e);
            return null;
        }
        httpPost.setHeader("Content-Type", CONTENT_TYPE_JSON);
        httpPost.setEntity(stringEntity);


        CloseableHttpResponse response = null;
        try {
            logger.info("请求url:{}", httpPost.getURI());
            response = httpclient.execute(httpPost);
            logger.info("返回code:{}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity, CHARSET_UTF_8);
                logger.error("非200响应，http状态码:{}, 响应体:{}", response.getStatusLine().getStatusCode(), res);
                throw new ServerException(String.valueOf(response.getStatusLine().getStatusCode()), response.getStatusLine().getReasonPhrase());
            }
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, CHARSET_UTF_8);
            logger.info("返回值:{}", res);
        } catch (IOException e) {
            logger.error("请求异常!", e);
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error("close httpClient exception!", e);
                }
            }
        }
        return res;
    }

    public static String downloadFile(String url, String jsonStr) throws ServerException {
        String res = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity;
        try {
            stringEntity = new StringEntity(jsonStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("http post json set entity error!", e);
            return null;
        }
        httpPost.setHeader("Content-Type", CONTENT_TYPE_JSON);
        httpPost.setEntity(stringEntity);


        CloseableHttpResponse response = null;
        try {
            logger.info("请求url:{}", httpPost.getURI());
            response = httpclient.execute(httpPost);
            logger.info("返回code:{}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity, CHARSET_UTF_8);
                logger.error("非200响应，http状态码:{}, 响应体:{}", response.getStatusLine().getStatusCode(), res);
                throw new ServerException(String.valueOf(response.getStatusLine().getStatusCode()), response.getStatusLine().getReasonPhrase());
            }
            String signature = "";
            Header contentHead = response.getFirstHeader("signature");
            signature = contentHead != null ? contentHead.getValue() : "";
            logger.info("文件签名:{}", signature);
            if (StringUtils.isBlank(signature)) {
                throw new ServerException(String.valueOf(response.getStatusLine().getStatusCode()), "签名为空！");
            }
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, CHARSET_UTF_8);
            if (!signature.equals(SMUtil.sm3Digest(res))) {
                throw new ServerException(String.valueOf(response.getStatusLine().getStatusCode()), "签名验证失败！");
            }

            logger.info("返回值:{}", res);
        } catch (IOException e) {
            logger.error("请求异常!", e);
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error("close httpClient exception!", e);
                }
            }
        }
        return res;
    }

    public void init() {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(this.getMaxConnection());
        connMgr.setDefaultMaxPerRoute(this.getMaxPerConnectionRoute());
        //校验链接
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(this.getConnectTimeout());
        // 读取超时时间
        configBuilder.setSocketTimeout(this.getSocketTimeout());
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(this.getConnectRequestTimemout());
        requestConfig = configBuilder.build();
    }

    /****
     * 	上传文件
     * @author demoAuthor
     * @param url 接口地址
     * @param authorization 认证部分
     * @param filePath	文件路径
     * @param data	报文数据
     * @return
     * @throws ClientException,IOException
     * @Notice 无
     */
    public String postFileForm(String url, String authorization, String filePath, String data) throws ClientException, IOException {
        log.info("请求信息-------------------------------------------------- ");
        log.info("url:" + url);
        log.info("authorization:" + authorization);
        log.info("filePath:" + filePath);
        log.info("data:" + data);
        String res = null;
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = null;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = new File(filePath);
        FormBodyPart partFile = FormBodyPartBuilder.create("file", new FileBody(file)).build();
        builder.addPart(partFile);
        FormBodyPart partData = FormBodyPartBuilder.create("data", new StringBody(data, ContentType.TEXT_PLAIN)).build();
        builder.addPart(partData);
        httpEntity = builder.build();
        httpPost.setHeader("Authorization", authorization);
        httpPost.setEntity(httpEntity);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setConnectionManager(connMgr)
                    .setConnectionManagerShared(true)
                    .setKeepAliveStrategy((resp, context) -> this.getKeepaliveTime())
                    .build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new ClientException(response.getStatusLine().getStatusCode(), EntityUtils.toString(entity, CHARSET_UTF_8));
            }
            res = EntityUtils.toString(entity, CHARSET_UTF_8);
        }catch(IOException e) {
            throw e;
        }finally {
            doResponseClose(response);
            doHttpClientClose(httpClient);
        }

        return res;
    }
    public String queryFileForm(String url, String authorization, String data) throws ClientException, IOException {
        log.info("请求信息-------------------------------------------------- ");
        log.info("url:" + url);
        log.info("authorization:" + authorization);
        log.info("data:" + data);
        String res = null;
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = null;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FormBodyPart partData = FormBodyPartBuilder.create("data", new StringBody(data, ContentType.TEXT_PLAIN)).build();
        builder.addPart(partData);
        httpEntity = builder.build();
        httpPost.setHeader("Authorization", authorization);
        httpPost.setEntity(httpEntity);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setConnectionManager(connMgr)
                    .setConnectionManagerShared(true)
                    .setKeepAliveStrategy((resp, context) -> this.getKeepaliveTime())
                    .build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new ClientException(response.getStatusLine().getStatusCode(), EntityUtils.toString(entity, CHARSET_UTF_8));
            }
            res = EntityUtils.toString(entity, CHARSET_UTF_8);
        }catch(IOException e) {
            throw e;
        }finally {
            doResponseClose(response);
            doHttpClientClose(httpClient);
        }

        return res;
    }


    /****
     * 释放httpClient
     * @author demoAuthor
     * @param httpClient
     * @Notice 无
     */
    private static void doHttpClientClose(CloseableHttpClient httpClient) {
        if(httpClient != null) {
            try {
                httpClient.close();
            }catch(IOException e) {
                log.error("close httpClient exception!", e);
            }
        }
    }

    private static void doResponseClose(CloseableHttpResponse response) {
        if(response != null) {
            try {
                response.close();
            }catch(IOException e) {
                log.error("close httpClient exception!", e);
            }
        }
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public int getConnectTimeout() {
        return connectTimeout > 0? connectTimeout : TIME_OUT;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout > 0? socketTimeout : TIME_OUT;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getConnectRequestTimemout() {
        return connectRequestTimemout > 0? connectRequestTimemout : TIME_OUT;
    }

    public void setConnectRequestTimemout(int connectRequestTimemout) {
        this.connectRequestTimemout = connectRequestTimemout;
    }

    public int getMaxConnection() {
        return maxConnection > 0? maxConnection : MAX_CONNECTION;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public int getMaxPerConnectionRoute() {
        return maxPerConnectionRoute > 0? maxPerConnectionRoute : MAX_CONNECTION;
    }

    public void setMaxPerConnectionRoute(int maxPerConnectionRoute) {
        this.maxPerConnectionRoute = maxPerConnectionRoute;
    }

    public long getKeepaliveTime() {
        return keepaliveTime > 0? keepaliveTime : KEEPALIVE_TIME;
    }

    public void setKeepaliveTime(long keepaliveTime) {
        this.keepaliveTime = keepaliveTime;
    }
}
