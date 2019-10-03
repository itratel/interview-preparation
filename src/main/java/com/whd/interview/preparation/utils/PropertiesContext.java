package com.whd.interview.preparation.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 18:34
 * @apiNote Describe the function of this class in one sentence
 */
@Component
public class PropertiesContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**ResourceLoaderAware
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PropertiesContext.applicationContext = applicationContext;
    }

    /***
     * 获取上下文环境
     * @return {@link Environment}
     */
    private static Environment getEnvironment() {
        if (Objects.nonNull(applicationContext)) {
            return applicationContext.getEnvironment();
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
//        return getBinder().bind(proPrefix, Bindable.of(clazz)).orElseGet(() -> {
//            try {
//                return clazz.newInstance();
//            } catch (InstantiationException | IllegalAccessException e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
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
