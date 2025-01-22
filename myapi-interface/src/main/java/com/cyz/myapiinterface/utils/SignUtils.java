package com.cyz.myapiinterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

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
