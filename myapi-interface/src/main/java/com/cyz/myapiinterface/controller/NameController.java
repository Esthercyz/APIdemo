package com.cyz.myapiinterface.controller;

import com.cyz.myapicommon.common.BaseResponse;
import com.cyz.myapiclientsdk.model.User;
import com.cyz.myapicommon.common.exception.BusinessException;
import com.cyz.myapicommon.common.exception.ErrorCode;
import com.cyz.myapicommon.v1.param.IpAnaParam;
import com.cyz.myapiinterface.IpUtil.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;


/*
  名称API
  @auther: YuziChen
 */

@RequestMapping("/name")
@RestController
public class NameController{
    //内部测试接口
    @GetMapping("/get")
    public String getNameByGet(String name,HttpServletRequest request){
        System.out.println(request.getHeader("cyz"));
        return "GET 你的名字是"+name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam("username") String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user/")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {

        return "POST 用户名字是" + user.getName();
    }



}
