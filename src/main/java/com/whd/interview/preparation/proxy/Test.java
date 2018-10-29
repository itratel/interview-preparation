package com.whd.interview.preparation.proxy;

import com.whd.interview.preparation.proxy.cglib.CglibDynamicProxy;
import com.whd.interview.preparation.proxy.jdkdynamic.JdkDynamicProxy;
import com.whd.interview.preparation.proxy.proxiedservice.IUserService;
import com.whd.interview.preparation.proxy.proxiedservice.StudentService;
import com.whd.interview.preparation.proxy.proxiedservice.UserServiceImpl;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:46
 * @apiNote Describe the function of this class in one sentence
 */
public class Test {

    public static void main(String[] args) {

        IUserService userService = new UserServiceImpl();
        JdkDynamicProxy dynamicProxy = new JdkDynamicProxy();
        //创建代理对象
        IUserService proxyInstance = (IUserService) dynamicProxy.newProxyInstance(userService);
        //执行目标方法
        proxyInstance.getUserInfo("China");


        StudentService studentService = new StudentService();
        CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy(studentService);
        //创建代理对象
        StudentService studentProxy = (StudentService) cglibDynamicProxy.newProxyInstance();
        //执行目标方法
        studentProxy.getInfo();
    }
}
