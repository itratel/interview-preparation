package com.whd.interview.preparation.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 23:22
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@Component
public class PropertiesContext implements EnvironmentAware {

    private static Environment environment;

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        PropertiesContext.environment = environment;
    }

    /***
     * 返回环境变量
     * @return {@link Environment}
     */
    public static Environment getEnvironment() {
        return environment;
    }

    /***
     * 获取属性绑定器
     * @return {@link Binder}
     */
    public static Binder getBinder() {
        return Binder.get(environment);
    }

    /***
     * 获取属性绑定的对象
     * @param proPrefix 属性前缀
     * @param clazz 类属性
     * @param <T> 参数
     * @return T
     */
    public static <T> T getProEntity(String proPrefix, Class<T> clazz) {
        return getBinder().bind(proPrefix, Bindable.of(clazz)).orElseCreate(clazz);
    }

    /***
     * 获取属性List
     * @param proPrefix 属性list
     * @param clazz 类属性
     * @param <T> 泛型参数
     * @return T
     */
    public static <T> List<T> getProEntityList(String proPrefix, Class<T> clazz) {
        return getBinder().bind(proPrefix, Bindable.listOf(clazz)).orElse(Collections.emptyList());
    }
}
