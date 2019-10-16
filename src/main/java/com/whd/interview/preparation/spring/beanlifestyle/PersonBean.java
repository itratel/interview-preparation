package com.whd.interview.preparation.spring.beanlifestyle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/17 01:24
 * @apiNote Describe the function of this class in one sentence
 */
public class PersonBean implements InitializingBean, DisposableBean {

    @Autowired
    private AnimalBean animalBean;

    private String state;

    private String name = this.getClass().getSimpleName();

    public PersonBean (){
        state = "construct()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    public void initMethod (){
        state = "initMethod()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    public void destroyMethod (){
        state = "destroyMethod()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    @PostConstruct
    public void postConstruct (){
        state = "postConstruct()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    @PreDestroy
    public void preDestroy (){
        state = "preDestroy()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        state = "afterPropertiesSet()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }

    @Override
    public void destroy() throws Exception {
        state = "afterPropertiesSet()执行";
        System.out.println(name  + " -> " + state + "..............." + "animalBean: " + animalBean);
    }
}
