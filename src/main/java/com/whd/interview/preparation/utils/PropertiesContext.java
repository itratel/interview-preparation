package com.whd.interview.preparation.utils;

import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.whd.interview.preparation.utils.SpringContext.getApplicationContext;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 23:22
 * @apiNote Describe the function of this class in one sentence
 */
public class PropertiesContext {

    private static final ApplicationContext APPLICATION_CONTEXT = getApplicationContext();

    /***
     * 获取上下文环境
     * @return {@link Environment}
     */
    private static Environment getEnvironment() {
        if (Objects.nonNull(APPLICATION_CONTEXT)) {
            return APPLICATION_CONTEXT.getEnvironment();
        }
        throw new RuntimeException("上下文为空");
    }

    /***
     * 获取属性绑定器
     * @return {@link Binder}
     */
    public static Binder getBinder() {
        return Binder.get(getEnvironment());
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
