package com.whd.interview.preparation.utils;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/15 00:12
 * @apiNote Describe the function of this class in one sentence
 */
public class CustomComparator {

    public static <T> int comparing(Map.Entry<String, List<T>> o1, Map.Entry<String, List<T>> o2) {
        if (StrUtil.isBlank(o1.getKey()) || StrUtil.isBlank(o2.getKey())) {
            return 0;
        }
        return -Double.valueOf(o1.getKey()).compareTo(Double.valueOf(o2.getKey()));
    }

}
