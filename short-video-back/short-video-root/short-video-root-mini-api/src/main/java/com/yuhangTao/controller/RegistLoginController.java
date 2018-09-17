package com.yuhangTao.controller;

import com.yuhangTao.impl.UserService;
import com.yuhangTao.org.n3r.idworker.Sid;
import com.yuhangTao.utilsl.IMoocJSONResult;
import com.yuhangTao.utilsl.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yuhangTao.pojo.Users;

/*
* 用户注册登录
* @Api用于controller类上
* */

@RestController
@Api(value = "用户注册登录的接口",tags ={"注册和登录的controller"} )
public class RegistLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    /*
    * 因为提交的是表单，所以用PostMapping
    * 这里我们返回的是IMoocJSONResult工具类
    * 该工具类和直接返回json数据并没有太大区别
    * 只不过多了状态等数据，更人性化
    * @RequestBody:因为得到是JSON数据
    * @ApiOperation用在controller的方法上
    * */
    @ApiOperation(value = "用户注册",notes = "用户注册接口")
    @PostMapping("/regist")
    public IMoocJSONResult regist(@RequestBody Users user){

        //1.判断用户名，密码是否为空(这里使用apache的commons工具类中的StringUtils来判断)
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }

        //2.判断用户名是否存在
        boolean userIsExists=userService.queryUserNameIsExists(user.getUsername());

        if(userIsExists){
            return IMoocJSONResult.errorMsg("该用户已存在");
        }else{
            //3.不存在则保存用户注册信息
            perfectUser(user);//先完善用户信息
            userService.createUser(user);//创建
        }
        user.setPassword("");//安全考虑，不返回密码
        return IMoocJSONResult.ok(user);
    }

    /*
    * 完善用户信息
    * 其中Nickname默认为用户名，Id由工具类创建
    * FansCounts，FollowCounts，ReceiveLikeCounts默认为0
    * FaceImage默认为null
    * @param user
    * */
    public void perfectUser(Users user){
        try {
            //使用加密算法对密码进行加密
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(user.getUsername());
        user.setId(sid.nextShort());//使用common工程下的工具类生成唯一的id
        user.setFansCounts(0);
        user.setFollowCounts(0);
        user.setReceiveLikeCounts(0);
        user.setFaceImage(null);
    }

}
