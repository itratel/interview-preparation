package com.whd.interview.preparation.entity;

import com.google.common.collect.Lists;
import com.whd.interview.preparation.utils.CustomComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/14 23:37
 * @apiNote Describe the function of this class in one sentence
 */
public class TestMapSort {

    private static Map<String, List<User>> map = new LinkedHashMap<>();

    private static List<User> userList = Lists.newArrayList(
            User.of("whd", "男", "23.02"),
            User.of("admin", "未知", "20.15"),
            User.of("mtt", "女", "5.98"),
            User.of("wsx", "女", "50.56"),
            User.of("wsz", "女", "50.56"),
            User.of("user", "女", "0.26"),
            User.of("panda", "女", ""),
            User.of("dog", "女", null)
    );

    public static void main(String[] args) {
        //分组
        Map<String, List<User>> userGroupMap = userList.stream()
                .filter(obj -> Objects.nonNull(obj.getAge())).collect(Collectors.groupingBy(User::getAge));

        userGroupMap.entrySet().stream().sorted(CustomComparator::comparing)
                .forEachOrdered(x -> map.put(x.getKey(), x.getValue()));
        System.out.println("map = " + map);
    }

}
