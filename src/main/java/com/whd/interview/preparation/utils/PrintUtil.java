package com.whd.interview.preparation.utils;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p>PrintUtil<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/4/24 18:08
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PrintUtil {

    /***
     * print collection data
     * @param data data
     * @param <T> type of param
     */
    public static <T> void printCollection(Collection<T> data) {
        data.forEach(System.out::println);
    }


    /***
     * print array data
     * @param data array data
     */
    public static void printArray(int[] data, String flag){
        System.out.println(flag + Arrays.toString(data));
    }

}
