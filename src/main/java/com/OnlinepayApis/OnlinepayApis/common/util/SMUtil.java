package com.OnlinepayApis.OnlinepayApis.common.util;

import com.pfpj.sm.SM3;
import com.pfpj.sm.Signature;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author demoAuthor
 * @Description 国密工具类
 * @Version V2.0.3
 * @Notice
 */
public class SMUtil {
    public static String getSM4Key() {
        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < 16; ++i) {
            int nextInt = random.nextInt(str.length());
            buffer.append(str.charAt(nextInt));
        }

        return buffer.toString();
    }

    public static String toSignStr(Signature signature) {
        String signStr = null;
        if (signature != null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(signature.getR().toString(16));
                sb.append("#");
                sb.append(signature.getS().toString(16));
                signStr = sb.toString();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return signStr;
    }

    public static Signature fromString(String signStr) {
        Signature signature = null;
        if (StringUtils.isNotBlank(signStr)) {
            try {
                String[] signSub = signStr.split("#");
                signature = new Signature(new BigInteger(signSub[0], 16), new BigInteger(signSub[1], 16));
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return signature;
    }
    public static String sm3Digest(String data) {
        String sm3Digest = null;

        sm3Digest = SM3.byteArrayToHexString(SM3.hash(data.getBytes()));

        return sm3Digest;
    }
}
