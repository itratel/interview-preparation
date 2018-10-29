package com.whd.interview.preparation.proxy.jdkdynamic;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:14
 * @apiNote Describe the function of this class in one sentence
 */
public class JdkDynamicProxy implements InvocationHandler {

    /***
     * 被代理的对象（目标对象、真实对象）
     */
    private Object targetObject;


    /***
     * create proxy object by proxied object
     * @param targetObject proxied object
     * @return proxy object
     */
    public Object newProxyInstance(Object targetObject){
        this.targetObject = targetObject;
        //根据目标对象的类加载器、接口
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        // 通过反射机制调用目标对象的方法
        Object proxyObject = method.invoke(this.targetObject, args);
        after();
        return proxyObject;
    }

    private void after() {
        System.out.println("开始执行目标对象之后---");
    }

    private void before() {
        System.out.println("开始执行目标对象之前---");
    }
}
