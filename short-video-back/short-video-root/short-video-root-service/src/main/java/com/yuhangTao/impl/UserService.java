package com.yuhangTao.impl;

import com.yuhangTao.pojo.Users;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户服务接口
 * 功能：1.查询用户是否存在
 * 2.创建用户
 * 3.检测用户是否合法（即用户名，密码是否正确）
 */
public interface UserService {

    boolean queryUserNameIsExists(String username);

    Users queryUserForLogin(String username,String password);

    void createUser(Users user);

}
