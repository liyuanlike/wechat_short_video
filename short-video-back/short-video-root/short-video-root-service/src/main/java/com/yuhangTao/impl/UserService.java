package com.yuhangTao.service;

import pojo.Users;

public interface UserService {

    boolean queryUserNameIsExists(String username);

    void saveUser(Users user);
}
