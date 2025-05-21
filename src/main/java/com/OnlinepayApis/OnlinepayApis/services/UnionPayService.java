package com.OnlinepayApis.OnlinepayApis.services;

import com.OnlinepayApis.OnlinepayApis.common.pojo.common.OpenApiMessage;
import com.OnlinepayApis.OnlinepayApis.common.pojo.common.OpenApiRequest;
import com.OnlinepayApis.OnlinepayApis.common.pojo.common.OpenApiResponse;
import com.OnlinepayApis.OnlinepayApis.common.pojo.ecny.BusiData;
import com.OnlinepayApis.OnlinepayApis.common.pojo.ecny.CheckoutPayRequest;
import com.OnlinepayApis.OnlinepayApis.common.pojo.ecny.OrderDetail;
import com.OnlinepayApis.OnlinepayApis.common.util.HttpClientUtils;
import com.OnlinepayApis.OnlinepayApis.common.util.SMUtil;
import com.OnlinepayApis.OnlinepayApis.common.util.SerialNoUtil;
import com.OnlinepayApis.OnlinepayApis.common.pojo.common.OpenApiMessageHead;
import com.OnlinepayApis.OnlinepayApis.crypto.SM2Utils;
import com.OnlinepayApis.OnlinepayApis.crypto.SM4Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pfpj.sm.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UnionPayService {

    private static final Logger logger = LoggerFactory.getLogger(UnionPayService.class);

    private static final String merchantId = "tradeGroupPre001";
    private static final String appID = "1095757516090363904001";
    private static final String privateKey = "009378BDB7262E282910AAE680E0A83EE30EA2AB8D01E41FE880583D1DA512C51E";
    private static final String publicKey = "04F0F770FDD6E188E31A27A84AC9D6D820D33CF6088A78B305C948A6D98479AC3E71D0AF0356D3C93229C27C1B345B4110DEFBF86885876977573468063EFD8F4F";
    private static final String sopPublicKey = "040485CEFE14C7AF854C66D5279239E88F2E8B881C3EB1B393003D2B9F09E7064447C1A3615875B05A9164F7F637151F115B89E70DFCCD0C25CF83268E21576921";
    private static final String urlTemplate = "http://wap.dev.psbc.com/sop-h5/biz_pre/unionpay/${merchantId}.htm?partnerTxSriNo=${partnerTxSriNo}";

    public String onlinePay() {
        try {
            OpenApiMessageHead msgHead = buildMsgHead("onlinepay.orderPay");
            CheckoutPayRequest request = buildCheckoutRequest(msgHead.getPartnerTxSriNo());
            OpenApiMessage<JSONObject> reqMsg = new OpenApiMessage<>();
            reqMsg.setHead(msgHead);
            reqMsg.setBody(JSONObject.parseObject(JSON.toJSONString(request)));

            return sendMsg(msgHead, reqMsg);
        } catch (Exception e) {
            logger.error("OnlinePay failed", e);
            return "OnlinePay failed: " + e.getMessage();
        }
    }

    private OpenApiMessageHead buildMsgHead(String method) {
        OpenApiMessageHead msgHead = new OpenApiMessageHead();
        msgHead.setPartnerTxSriNo(SerialNoUtil.getSerialNo());
        msgHead.setMethod(method);
        msgHead.setVersion("1");
        msgHead.setMerchantId(merchantId);
        msgHead.setAccessType("API");
        msgHead.setAppID(appID);
        msgHead.setReqTime(SerialNoUtil.getDateTime());
        return msgHead;
    }

    private CheckoutPayRequest buildCheckoutRequest(String partnerTxSriNo) {
        CheckoutPayRequest request = new CheckoutPayRequest();
        request.setBusiMainId(partnerTxSriNo);
        request.setReqTransTime(SerialNoUtil.getDateTime());

        BusiData data = new BusiData();
        data.setTxnCode("6007");
        data.setSourceId("69");
        data.setReqTraceId(partnerTxSriNo);
        data.setReqDate(SerialNoUtil.getDateTime());
        data.setMercDtTm(SerialNoUtil.getDateTime());
        data.setVendorNo(partnerTxSriNo);
        data.setMercCode("100510100077920");
        data.setTransAmt("1");
        data.setMercUrl("https://gateway.postonline.gx.cn/newspaper/bankCallback");
        data.setOrderUrl("/order/list");
        data.setValidTime("600");
        data.setMercName("gxpost_newspaper");
        data.setBizTp("100001");
        data.setOrderTitle("测试订单");
        data.setOrderCount("1");
        data.setTrxDevcInf("127.0.0.1");
        data.setReserveParam("外网");
        data.setPyeeAcctIssrId("C1040311005293");
        data.setPyeeAcctTp("00");
        data.setPyeeNm("名字必填");
        data.setPyeeAcctId("6221880000000030");
        data.setTerminalIp("127.0.0.1");
        data.setMerCustId("123");
        data.setPayEnv("01");
        data.setPhonePayEnv("02");

        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setSubMercCode("100510100077920");
        detail.setSubMercName("南宁报刊收款");
        detail.setTotalAmt("1");
        detail.setTotalNum("1");
        detail.setMerUnitDetail("商品简称^1^1");
        detailList.add(detail);

        data.setOrderDetail(detailList);
        request.setData(data);
        return request;
    }

    private String sendMsg(OpenApiMessageHead msgHead, OpenApiMessage<JSONObject> reqMsg) throws Exception {
        String rawJson = JSON.toJSONString(reqMsg);
        String sm4Key = SMUtil.getSM4Key();
        String encryptedRequest = SM4Utils.encrypt(rawJson, "CBC", sm4Key, "");

        OpenApiRequest openApiRequest = new OpenApiRequest();
        openApiRequest.setRequest(encryptedRequest);

        SM2Utils sm2 = new SM2Utils();
        openApiRequest.setEncryptKey(sm2.encrypt(sopPublicKey, sm4Key));
        openApiRequest.setAccessToken("");

        StringBuilder sb = new StringBuilder();
        sb.append(openApiRequest.getRequest())
                .append(openApiRequest.getEncryptKey());

        Signature sign = sm2.sign(merchantId, privateKey, sb.toString(), publicKey);
        openApiRequest.setSignature(SMUtil.toSignStr(sign));

        String finalUrl = urlTemplate.replace("${merchantId}", merchantId)
                                  .replace("${partnerTxSriNo}", msgHead.getPartnerTxSriNo());

        String response = HttpClientUtils.post(finalUrl, JSON.toJSONString(openApiRequest));
        OpenApiResponse openApiResponse = JSON.parseObject(response, OpenApiResponse.class);

        sb.setLength(0);
        sb.append(openApiResponse.getResponse())
                .append(openApiResponse.getEncryptKey());

        if (sm2.verifySign(merchantId, sopPublicKey, sb.toString(), SMUtil.fromString(openApiResponse.getSignature()))) {
            String respSm4Key = sm2.decrypt(privateKey, openApiResponse.getEncryptKey());
            return SM4Utils.decrypt(openApiResponse.getResponse(), "CBC", respSm4Key, "");
        } else {
            logger.error("验签失败");
            return "Signature verification failed";
        }
    }
}