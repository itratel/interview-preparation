package com.whd.interview.preparation.sortalgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 17:26
 * @apiNote Describe the function of this class in one sentence
 */
public class ArrayUtils {

    /***
     * default array length
     */
    private static final int LENGTH = 10;

    /***
     * random
     */
    private static final Random RANDOM = new Random();

    /***
     * produce an array that consist of random integer by length
     * @param length array of length
     * @return int[]
     */
    public static int[] newArrayByLength(int length){
        length = length == 0 ? LENGTH : length;
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = RANDOM.nextInt(100);
        }
        return  data;
    }

    /***
     * print array data
     * @param data array data
     */
    public static void printArray(int[] data, String flag){
        System.out.println(flag + Arrays.toString(data));
    }
}
