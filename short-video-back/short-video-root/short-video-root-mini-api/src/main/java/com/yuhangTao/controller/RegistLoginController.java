package com.yuhangTao.controller;

import com.yuhangTao.utilsl.IMoocJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pojo.Users;

/*
* 用户注册
* */

@RestController
public class RegistLoginController {

    /*
    * 因为提交的是表单，所以用PostMapping
    * 这里我们返回的是IMoocJSONResult工具类
    * 该工具类和直接返回json数据并没有太大区别
    * 只不过多了状态等数据，更人性化
    * @RequestBody:因为得到是JSON数据
    * */
    @PostMapping("/regist")
    public IMoocJSONResult regist(@RequestBody Users user){

        //1.判断用户名，密码是否为空
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            return IMoocJSONResult.errorMsg("用户名或密码不能为空");
        }

        //2.判断用户名是否存在



        //3.保存用户注册信息

        return IMoocJSONResult.ok();
    }
}
