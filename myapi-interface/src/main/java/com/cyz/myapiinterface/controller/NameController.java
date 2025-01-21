package com.cyz.myapiinterface.controller;

import org.springframework.web.bind.annotation.*;

import com.cyz.myapiinterface.model.User;

/*
  名称API
  @auther: YuziChen
 */

@RequestMapping("/name")
@RestController
public class NameController{
    @GetMapping("/")
    public String getNameByGet(String name){
        return "GET 你的名字是"+name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam("username") String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user) {
        return "POST 用户名字是" + user.getName();
    }
}
