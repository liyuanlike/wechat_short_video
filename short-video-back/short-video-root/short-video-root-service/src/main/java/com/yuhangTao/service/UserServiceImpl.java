package com.yuhangTao.service;


import com.yuhangTao.impl.UserService;
import com.yuhangTao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.yuhangTao.pojo.Users;

/*
* 1、PROPAGATION_REQUIRED:如果存在一个事务，则支持当前事务。如果没有事务则开启。

* 2、PROPAGATION_SUPPORTS:如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行。

* 3、PROPAGATION_MANDATORY:如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。

* 4、PROPAGATION_REQUIRES_NEW:总是开启一个新的事务。如果一个事务存在，则将这个存在的事务挂起。

* 5、PROPAGATION_NOT_SUPPORTED:总是非事务地执行，并挂起任何存在的事务。

* 6、PROPAGATION_NEVER:总是非事务地执行，如果存在一个活动事务，则抛出异常。

* 7、 PROPAGATION_NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中，
* 如果没有活动事务，则按TransactionDefinition.PROPAGATION_REQUIRED属性执行
*/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询用户是否存在
     *
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExists(String username) {
        Users user = new Users();
        user.setUsername(username);
        Users result = usersMapper.selectOne(user);
        return result == null ? false : true;
    }


    /**
     * 查询用户是否存在，即用户名密码是否正确
     * 此处必须返回一个Users对象，因为里面包
     * 含了用户的信息
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username,String password) {
        Users user=new Users();
        user.setUsername(username);
        user.setPassword(password);

        return usersMapper.selectOne(user);
    }


    /**
     * 创建用户
     * @param user
     * Propagation.REQUIRED:如果存在一个事务，则支持当前事务。如果没有事务则开启。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void createUser(Users user) {

        usersMapper.insert(user);//创建用户

    }


}
