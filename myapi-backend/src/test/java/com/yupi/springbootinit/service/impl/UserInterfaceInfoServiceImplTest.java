package com.yupi.springbootinit.service.impl;

import com.cyz.myapicommon.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInterfaceInfoServiceImplTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Test
    public void listInterfacesAnalysis() {
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoService.listInterfacesAnalysis(1);
        for (UserInterfaceInfo userInterfaceInfo : userInterfaceInfos) {
            System.out.println(userInterfaceInfo);
        }
    }
}