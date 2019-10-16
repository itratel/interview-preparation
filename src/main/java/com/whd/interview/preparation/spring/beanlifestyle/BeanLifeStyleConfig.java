package com.whd.interview.preparation.spring.beanlifestyle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/17 01:30
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
public class BeanLifeStyleConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public PersonBean bean1 (){
        return new PersonBean();
    }
}
