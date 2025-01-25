package com.yupi.springbootinit.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;


    @Test
    public void invokeInterfaceCount() {
        boolean succeed = userInterfaceInfoService.invokeCount(1L, 1L);
        assert succeed;
    }
}