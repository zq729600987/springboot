package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/*
* 外部tomcat部署需要继承SpringBootServletInitializer,重写configure方法
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.springboot.mapper"},markerInterface = CommonMapper.class)
public class MainController extends SpringBootServletInitializer {

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainController.class);
    }

    public static void main(String[] args){
        /*完全禁止自动重启*/
        //System.setProperty("spring.devtools.restart.enabled","false");
        /*禁用命令行赋值*/
        //SpringApplication.setAddCommandLineProperties(false);
        SpringApplication.run(MainController.class,args);

        /*取消Spring启动标识*/
        /*SpringApplication springApplication = new SpringApplication(MainController.class);
        springApplication.setBannerMode(Mode.OFF);
        springApplication.run(args);*/
    }
}
