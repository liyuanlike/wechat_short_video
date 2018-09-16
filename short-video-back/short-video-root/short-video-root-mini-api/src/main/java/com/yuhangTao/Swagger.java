package com.yuhangTao;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* 通过@Configuration注解，让Spring来加载该类配置。
* 再通过@EnableSwagger2注解来启用Swagger2。
* 通过http://localhost:8081/swagger-ui.html访问
* */

@Configuration
@EnableSwagger2
public class Swagger {


    /*
    * 通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息
    * （这些基本信息会展现在文档页面中）。select()函数返回一个ApiSelectorBuilder实
    * 例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger
    * 会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求。
    */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.yuhangTao.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /*
    * 在完成了上述配置后，其实已经可以生产文档内容，但是这样的文档主要针对请求本身，
    * 而描述主要来源于函数等命名产生，对用户并不友好，我们通常需要自己增加一些说明
    * 来丰富文档内容我们通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、
    * @ApiImplicitParam注解来给参数增加说明。
    * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置页面标题
                .title("微信短视频后端api接口文档")
                //设置联系人
                .contact(new Contact("yuhangTao",
                        "https://github.com/code-refugee/wechat_short_video.git",
                        "taoyuh1011@foxmail.com"))
                //描述信息
                .description("欢迎访问短视频接口文档，这里是描述信息")
                //定义版本号
                .version("1.0")
                .build();
    }


}
