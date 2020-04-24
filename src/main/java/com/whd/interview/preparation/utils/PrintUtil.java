package com.whd.interview.preparation.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * <p>PrintUtil<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/4/24 18:08
 * @version 0.0.1
 * @since 0.0.1
 */
@UtilityClass
public class PrintUtil {

    /***
     * print collection data
     * @param data data
     * @param <T> type of param
     */
    public <T> void printCollection(Collection<T> data) {
        data.forEach(System.out::println);
    }

}
