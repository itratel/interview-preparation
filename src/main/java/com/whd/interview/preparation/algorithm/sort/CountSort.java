package com.whd.interview.preparation.algorithm.sort;

import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.newArrayByLength;
import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/30 16:05
 * @apiNote CountSort
 */
public class CountSort {

    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        int[] sorts = sort(array);
        printArray(sorts, "After  sort: ");
    }

    /***
     * CountSort
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //找出数组中的最大最小值
        for (int anArray1 : array) {
            max = Math.max(max, anArray1);
            min = Math.min(min, anArray1);
        }

        int[] help = new int[max - min + 1];

        //找出每个数字出现的次数
        for (int anArray : array) {
            int mapPos = anArray - min;
            help[mapPos]++;
        }

        //计算每个数字应该在排序后数组中应该处于的位置
        for(int i = 1; i < help.length; i++){
            help[i] = help[i-1] + help[i];
        }

        //根据help数组进行排序
        int res[] = new int[array.length];
        for (int anArray : array) {
            int post = --help[anArray - min];
            res[post] = anArray;
        }
        return res;
    }

}
