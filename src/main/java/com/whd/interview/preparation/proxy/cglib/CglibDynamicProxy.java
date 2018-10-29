package com.whd.interview.preparation.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 15:12
 * @apiNote CglibDynamicProxy
 */
public class CglibDynamicProxy implements MethodInterceptor {

    /***
     * 被代理对象(proxied Object/target Object/real Object)
     */
    private Object targetObject;


    public CglibDynamicProxy(Object targetObject){
        this.targetObject = targetObject;
    }

    /***
     * create proxy object
     * @return proxy object
     */
    public Object newProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /***
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object object = methodProxy.invoke(this.targetObject, objects);
        after();
        return object;
    }


    private void after() {
        System.out.println("开始执行目标对象之后---");
    }

    private void before() {
        System.out.println("开始执行目标对象之前---");
    }
}
