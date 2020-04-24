package com.whd.interview.preparation.event;

import com.whd.interview.preparation.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/15 15:27
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@Component
public class EventHandler {

    @EventListener(condition = "#user != null")
    public void save(User user) {
        System.out.println("user = " + user);
    }
}
