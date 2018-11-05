package com.whd.interview.preparation.java8.classlibapi.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 16:21
 * @apiNote TestStream
 */
public class TestStream {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("12312", "china", Student.Status.CLOSED),
                new Student("15486", "american", Student.Status.CLOSED),
                new Student("51551", "dnsjad", Student.Status.OPEN)
        );

        List<String> collect = students.stream().map(Student::getId).collect(Collectors.toList());

        collect.forEach(t -> System.out.println("t = " + t));

        List<String> stringList = students.stream().filter(t -> t.getStatus() == Student.Status.CLOSED).map(Student::getName).collect(Collectors.toList());

        stringList.forEach(t-> System.out.println("t = " + t));


    }
}
