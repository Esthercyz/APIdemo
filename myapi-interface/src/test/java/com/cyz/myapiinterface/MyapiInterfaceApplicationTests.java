package com.cyz.myapiinterface;

import com.cyz.myapiclientsdk.CyzApiClientConfig;
import com.cyz.myapiclientsdk.client.MyApiClient;
import com.cyz.myapiclientsdk.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(CyzApiClientConfig.class)
class MyapiInterfaceApplicationTests {

    @Resource
    private MyApiClient myApiClient;
    @Test
    void contextLoads() {
        myApiClient.getNameByGet("cyz");
        User user=new User();
        user.setName("YuziChen");
        myApiClient.getUserNameByPost(user);
    }

}
