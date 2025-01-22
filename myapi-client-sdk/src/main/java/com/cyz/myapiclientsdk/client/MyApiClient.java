package com.cyz.myapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cyz.myapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.cyz.myapiclientsdk.utils.SignUtils.getSign;

public class MyApiClient {
    private String accessKey;
    private String secretKey;

    public MyApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private Map<String,String> getHeaderMap(String body) {
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("accessKey",accessKey);
        //secretKey一定不能发给后端
        hashMap.put("nonce", RandomUtil.randomNumbers(3));
//        String encodebody= URLEncoder.encode(body,"UTF-8");
        hashMap.put("body",body);
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("sign",getSign(body,secretKey));
        return hashMap;
    }

    public void getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:8123/api/name/",paramMap);
        System.out.println("GET 的请求结果为:" + result);

    }
    public void getNameByPost( String name) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("username",name);
        String result = HttpUtil.post("http://localhost:8123/api/name/",paramMap);
        System.out.println("POST 的请求结果为:" + result);

    }
    public void getUserNameByPost( User user) {
        String json= JSONUtil.toJsonStr(user);
        HttpResponse httpResponse= HttpRequest.post("http://localhost:8123/api/name/user/").
                addHeaders(getHeaderMap(json)).
                body(json).
                execute();
        int status = httpResponse.getStatus();
        System.out.println("ResponseStatus - " + status);

        String result = httpResponse.body();
        System.out.println(result);
    }
}
