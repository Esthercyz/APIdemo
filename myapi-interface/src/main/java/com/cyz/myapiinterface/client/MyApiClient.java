package com.cyz.myapiinterface.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cyz.myapiinterface.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public class MyApiClient {
    public String getNameByGet(String name) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String result = HttpUtil.get("http://localhost:8123/api/name/",paramMap);
        System.out.println(result);
        return result;
    }
    public String getNameByPost(@RequestParam String name) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String result = HttpUtil.get("http://localhost:8123/api/name/",paramMap);
        System.out.println(result);
        return result;
    }
    public String getUserNameByPost(@RequestBody User user) {
        String json= JSONUtil.toJsonStr(user);
        HttpResponse httpResponse= HttpRequest.post("http://localhost/8123/api/name/user").body(json).execute();
        System.out.println(httpResponse.getStatus());
        String result=httpResponse.body();
        System.out.println(result);
        return result;
    }
}
