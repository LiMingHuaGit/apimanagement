package com.liminghua.apimanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName SocketController
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/9/14
 * @Version V1.0
 **/
@Slf4j
@Controller
@Configuration
public class SocketController {
    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${user.home}")
    private String userName;

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                InetAddress ia = InetAddress.getLocalHost();
                //获取本机内网IP
                log.info("启动成功：" + "http://" + ia.getHostAddress() + ":" + port + "/");
                logger.info("${user.home} ：" + userName);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        };
    }

    /**
     * 跳转实时日志
     */
    @GetMapping("/logging")
    public ModelAndView logging() {
        return new ModelAndView("logging.html");
    }
}
