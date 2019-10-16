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

    private static Map<String, List<User>> sortMap = new LinkedHashMap<>();

    private static final List<String> NAME_LIST = Lists.newArrayList("panda");

    private static List<User> userList = Lists.newArrayList(
            User.of("whd", "男", "23.02", null),
            User.of("admin", "未知", "20.15", null),
            User.of("mtt", "女", "5.98", null),
            User.of("wsx", "女", "50.56", null),
            User.of("wsz", "女", "50.56", null),
            User.of("user", "女", "0.26", null),
            User.of("panda", "女", "0.19", null),
            User.of("dog", "女", null, null)
    );

    public static void main(String[] args) {
        //分组
        Map<String, List<User>> userGroupMap = userList.stream()
                .filter(e -> Objects.nonNull(e.getAge()) && !NAME_LIST.contains(e.getUsername())).collect(Collectors.groupingBy(User::getAge));

        userGroupMap.entrySet().stream().sorted(CustomComparator::comparing)
                .forEachOrdered(x -> sortMap.put(x.getKey(), x.getValue()));

        //设置排名
        int index = 1;
        for (Map.Entry<String, List<User>> entry : sortMap.entrySet()) {
            for (User user : entry.getValue()) {
                user.setRanking(index);
            }
            index++;
        }
        System.out.println("userList = " + userList);
    }



}
