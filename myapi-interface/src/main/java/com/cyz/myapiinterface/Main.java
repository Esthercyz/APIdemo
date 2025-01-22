package com.cyz.myapiinterface;

import com.cyz.myapiinterface.client.MyApiClient;
import com.cyz.myapiinterface.model.User;

public class Main {
    public static void main(String[] args){
        String accessKey="cyz";
        String secretKey="abcdefgh";
        MyApiClient myApiClient=new MyApiClient(accessKey,secretKey);
        myApiClient.getNameByGet("YuziChen");
        myApiClient.getNameByPost("YuziChen");
        User user=new User();
        user.setName("rich");
        myApiClient.getUserNameByPost(user);
    }
}
