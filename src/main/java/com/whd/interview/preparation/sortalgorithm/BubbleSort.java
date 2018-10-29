package com.whd.interview.preparation.sortalgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:20
 * @apiNote BubbleSort 冒泡排序算法
 */
public class BubbleSort {

    private static final int LENGTH = 10;

    public static void main(String[] args) {
        int[] data = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            data[i] = new Random().nextInt(100);
        }
        System.out.println("排序前:" + Arrays.toString(data));
        int[] sorts = sort(data);
        System.out.println("排序会:" + Arrays.toString(sorts));
    }


    /***
     * 冒泡排序
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array){
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
