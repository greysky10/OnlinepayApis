package com.OnlinepayApis.OnlinepayApis.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.OnlinepayApis.OnlinepayApis.common.pojo.common.*;
import com.OnlinepayApis.OnlinepayApis.common.pojo.ecny.HardwareWalletOpenRequest;
import com.OnlinepayApis.OnlinepayApis.common.util.HttpClientUtils;
import com.OnlinepayApis.OnlinepayApis.common.util.SMUtil;
import com.OnlinepayApis.OnlinepayApis.common.util.SerialNoUtil;
import com.pfpj.sm.SM2Utils;
import com.pfpj.sm.SM4Utils;
import com.pfpj.sm.Signature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

@Service
public class HardwareWalletService {

    private static final Logger logger = LoggerFactory.getLogger(HardwareWalletService.class);

    private final WalletConfig config;

    public HardwareWalletService(WalletConfig config) {
        this.config = config;
    }

    public String openWallet() {
        try {
            String merchantId = config.getMerchantId();
            String appID = config.getAppID();
            String moduleName = config.getModuleName();
            String privateKey = config.getPrivateKey();
            String publicKey = config.getPublicKey();
            String sopPublicKey = config.getSopPublicKey();

            String url = config.getUrl()
                                 .replace("${moduleName}", moduleName)
                                 .replace("${merchantId}", merchantId);

            OpenApiMessageHead msgHead = new OpenApiMessageHead();
            msgHead.setPartnerTxSriNo(SerialNoUtil.getSerialNo());
            msgHead.setMethod("ecny.openHardwareWallet");
            msgHead.setVersion("1");
            msgHead.setMerchantId(merchantId);
            msgHead.setAccessType("API");
            msgHead.setAppID(appID);
            msgHead.setReqTime(SerialNoUtil.getDateTime());

            HardwareWalletOpenRequest request = new HardwareWalletOpenRequest();
            request.setPhone("13544441235");
            request.setAPDURespData("0000002300800000000207592731...");
            request.setDeviceName("vivo");
            request.setBusiMainId(msgHead.getPartnerTxSriNo());
            request.setReqTransTime(SerialNoUtil.getDateTime());

            JSONObject requestJson = JSONObject.parseObject(JSON.toJSONString(request));
            OpenApiMessage<JSONObject> reqMsg = new OpenApiMessage<>();
            reqMsg.setHead(msgHead);
            reqMsg.setBody(requestJson);

            String rawJson = JSON.toJSONString(reqMsg);
            String sm4Key = SMUtil.getSM4Key();

            OpenApiRequest openApiRequest = new OpenApiRequest();
            openApiRequest.setRequest(SM4Utils.encrypt(rawJson, "CBC", sm4Key, ""));

            SM2Utils sm2 = new SM2Utils();
            openApiRequest.setEncryptKey(sm2.encrypt(sopPublicKey, sm4Key));
            openApiRequest.setAccessToken("");

            StringBuilder sb = new StringBuilder();
            sb.append(openApiRequest.getRequest())
                    .append(openApiRequest.getEncryptKey())
                    .append(openApiRequest.getAccessToken());

            Signature sign = sm2.sign(merchantId, privateKey, sb.toString(), publicKey);
            openApiRequest.setSignature(SMUtil.toSignStr(sign));

            url = url.replace("{partnerTxSriNo}", msgHead.getPartnerTxSriNo());

            String response = HttpClientUtils.post(url, JSON.toJSONString(openApiRequest));
            OpenApiResponse openApiResponse = JSON.parseObject(response, OpenApiResponse.class);

            sb.setLength(0);
            sb.append(openApiResponse.getResponse())
                    .append(openApiResponse.getEncryptKey())
                    .append(openApiResponse.getAccessToken());

            boolean verified = sm2.verifySign(merchantId, sopPublicKey, sb.toString(),
                    SMUtil.fromString(openApiResponse.getSignature()));

            if (verified) {
                String respSm4Key = sm2.decrypt(privateKey, openApiResponse.getEncryptKey());
                return SM4Utils.decrypt(openApiResponse.getResponse(), "CBC", respSm4Key, "");
            } else {
                return "Signature verification failed.";
            }

        } catch (Exception e) {
            logger.error("Exception in openWallet()", e);
            return "Exception occurred: " + e.getMessage();
        }
    }

    @Component
    @ConfigurationProperties(prefix = "wallet")
    public static class WalletConfig {
        private String merchantId;
        private String appID;
        private String moduleName;
        private String url;
        private String privateKey;
        private String publicKey;
        private String sopPublicKey;

        // Getters & setters
        public String getMerchantId() { return merchantId; }
        public void setMerchantId(String merchantId) { this.merchantId = merchantId; }

        public String getAppID() { return appID; }
        public void setAppID(String appID) { this.appID = appID; }

        public String getModuleName() { return moduleName; }
        public void setModuleName(String moduleName) { this.moduleName = moduleName; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }

        public String getPrivateKey() { return privateKey; }
        public void setPrivateKey(String privateKey) { this.privateKey = privateKey; }

        public String getPublicKey() { return publicKey; }
        public void setPublicKey(String publicKey) { this.publicKey = publicKey; }

        public String getSopPublicKey() { return sopPublicKey; }
        public void setSopPublicKey(String sopPublicKey) { this.sopPublicKey = sopPublicKey; }
    }
}
