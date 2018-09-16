package com.yuhangTao.impl;

import com.yuhangTao.pojo.Users;


/**
 * 用户服务接口
 * 功能：1.查询用户是否存在
 * 2.创建用户
 *
 */
public interface UserService {

    boolean queryUserNameIsExists(String username);

    void createUser(Users user);
}
