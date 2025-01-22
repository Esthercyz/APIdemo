package com.cyz.myapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/*
签名工具
 */
public class SignUtils {
    public static String getSign(String body, String secretKey){
        Digester md5 =new Digester(DigestAlgorithm.SHA256);
        String context=body+"."+secretKey;
        return md5.digestHex(context);
    }
}
