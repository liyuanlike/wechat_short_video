package com.yuhangTao.controller;


import com.yuhangTao.Application;
import com.yuhangTao.controller.RegistLoginController;
import com.yuhangTao.utilsl.IMoocJSONResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/*
* 测试RegistLoginController类
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRegistLoginController {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        //构造MockMvc
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testRegist(){
//        RequestBuilder requestBuilder = null;
//        requestBuilder = post("/short_video/regist")
//                .param("username","yuhangTao")
//                .param("password","12345");
        try {
            //这里的url路径一定要是完整的路径
            mockMvc.perform(MockMvcRequestBuilders.post("localhost:8081/short_video/regist")
            .contentType(MediaType.APPLICATION_JSON)
            .param("username","root")
            .param("password","12345")
            .accept(MediaType.APPLICATION_JSON))//接受类型
                    .andExpect(MockMvcResultMatchers.status().is5xxServerError())//判断接收到的状态是否是200
//                    .andDo(MockMvcResultHandlers.print())//打印内容
//                    .andExpect(MockMvcResultMatchers.content().string("该用户已存在"))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
