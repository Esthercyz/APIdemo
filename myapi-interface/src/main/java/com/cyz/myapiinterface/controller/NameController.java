package com.cyz.myapiinterface.controller;


import com.cyz.myapiclientsdk.model.User;
import com.cyz.myapiclientsdk.utils.SignUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;



import java.time.Duration;
import java.time.Instant;


/*
  名称API
  @auther: YuziChen
 */

@RequestMapping("/name")
@RestController
public class NameController{
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
