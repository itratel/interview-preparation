package com.whd.interview.preparation.java8.classlibapi.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.ArrayUtil.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 10:24
 * @apiNote TestArrays
 * @see Arrays
 * @see ThreadLocalRandom
 */
public class TestArrays {

    private static final int ARRAY_SIZE = 100;

    public static void main(String[] args) {
        int[] array = newArrayByLength(ARRAY_SIZE);
        printArray(array, "before sort: ");
        Arrays.parallelSort(array);
        printArray(array, "after sort: ");
        Arrays.stream(array).limit(10).forEach(t -> System.out.println("t = " + t));

        int[] data = new int[ARRAY_SIZE];
        Arrays.parallelSetAll(data, t -> ThreadLocalRandom.current().nextInt(1000));
        Arrays.parallelSort(data);
        Arrays.stream(data).limit(20).forEach(t -> System.out.println("t1 = " + t));

    }
}
