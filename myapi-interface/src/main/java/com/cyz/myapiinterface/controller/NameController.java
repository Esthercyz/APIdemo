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
    @GetMapping("/")
    public String getNameByGet(String name){

        return "GET 你的名字是"+name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam("username") String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user/")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        // todo 实际情况是去数据库中查找是否已经分配给用户
        if(!accessKey.equals("cyz")){
            throw new RuntimeException("无权限");
        }
        if(Long.parseLong(nonce)>10000){
            throw new RuntimeException("无权限-随机数错误");
        }
        //校验时间戳如果时间差距大于 3 分钟报错
        if (!isWithinThreeMinutes(timestamp)) {
            throw new RuntimeException("无权限-过期");
        }
        //todo 实际情况是从数据库中查出secretKey
        String serverSign= SignUtils.getSign(body,"abcdefgh");
        if(!sign.equals(serverSign)){
            throw new RuntimeException("无权限-签名不一致");
        }
        return "POST 用户名字是" + user.getName();
    }
    private boolean isWithinThreeMinutes(String timeStamp) {

        try {
            long receivedTimeMillis = Long.parseLong(timeStamp);

            //注意 客户端传入的时间戳是按照 毫秒 -> 秒存储的，此处需要同步 / 1000
            long currentTimeMillis = System.currentTimeMillis() / 1000; //除以 1000 获取秒数

            //计算时间差
            // 计算时间差
            Duration duration = Duration.between(
                    Instant.ofEpochMilli(receivedTimeMillis),
                    Instant.ofEpochMilli(currentTimeMillis)
            );
            return duration.toMinutes() <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
