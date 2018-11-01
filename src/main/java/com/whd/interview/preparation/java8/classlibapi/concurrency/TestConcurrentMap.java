package com.whd.interview.preparation.java8.classlibapi.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/1 22:13
 * @apiNote Describe the function of this class in one sentence
 * @see ConcurrentHashMap
 **/
public class TestConcurrentMap {


    public static void main(String[] args) {

        Map<String, Object> map = new ConcurrentHashMap<>(10);
        for (int i = 0; i < 8; i++) {
            map.put("java" + i, "javaValue" + i);
        }
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));
        System.out.println("合并key不存在的数据");
        map.merge("10", "", (t, u) -> t.toString() + u.toString());
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));

        System.out.println("合并key已经存在的数据");
        map.merge("java5", "object", (t, u) -> t.toString() + u.toString());
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));

        Object java5 = map.getOrDefault("10", "1213256");
        System.out.println(java5);

        System.out.println("将每一个value值全部由新的键所属类型的值代替");
        map.replaceAll((t, v) -> t + v + "aa");
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));


        System.out.println("如果一个key存在就取出对应的值，不存在就取出设置的新值(新值由随意键所属类型的值)，并且把不存在的键值对加入到map中");
        Object computeIfAbsent = map.computeIfAbsent("10", t -> t + "abc");
        System.out.println("computeIfAbsent = " + computeIfAbsent);
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));

        System.out.println("如果一个key存在就取出设置的新值(新值由随意键所属类型的值)，并且把不存在的键值对加入到map中，覆盖key的旧值，如果key不存在就返回null");
        Object computeIfPresent = map.computeIfPresent("100", (a, b) -> a + b + "bcd");
        System.out.println("computeIfPresent = " + computeIfPresent);
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));

        System.out.println("如果一个key存在就取出设置的新值(新值由随意键所属类型的值)，并且把不存在的键值对加入到map中，覆盖key的旧值，如果key不存在就新增加一个值，同时将键值对加到map中");
        Object compute = map.compute("100", (a, b) -> a + b + "efg");
        System.out.println("compute = " + compute);
        map.forEach((a, b) -> System.out.println(a + " " + b.toString()));

    }
}
