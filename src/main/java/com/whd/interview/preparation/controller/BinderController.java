package com.whd.interview.preparation.controller;

import com.whd.interview.preparation.entity.User;
import com.whd.interview.preparation.utils.PropertiesContext;
import com.whd.interview.preparation.utils.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 20:27
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@RestController
public class BinderController {



    @GetMapping("/user")
    public User getUser(){
        User user = PropertiesContext.getProEntity("spring.properties", User.class);
        log.info("获取用户信息为：{}", user);
        //事件发布
        SpringContext.getApplicationContext().publishEvent(user);
        return user;
    }
}
