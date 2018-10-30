package com.whd.interview.preparation.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.newArrayByLength;
import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/30 22:09
 * @apiNote Describe the function of this class in one sentence
 **/
public class RadixSort {


    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        sort(array);
        printArray(array, "After sort: ");
    }

    /***
     * 实现基数排序 LSD-从最低位开始排 MSD-从最高位开始排
     * @param array array to sort
     */
    private static void sort(int[] array) {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        //数组的第一维表示可能的余数0-9
        int[][] temp = new int[10][array.length];
        //数组orderp[i]用来表示该位是i的数的个数
        int[] order = new int[10];
        int d = maxBin(array);
        while(m <= d)
        {
            for (int anArray : array) {
                int lsd = ((anArray / n) % 10);
                temp[lsd][order[lsd]] = anArray;
                order[lsd]++;
            }
            for(int i = 0; i < 10; i++)
            {
                if(order[i] != 0){
                    for(int j = 0; j < order[i]; j++) {
                        array[k] = temp[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }


    /***
     * 计算数组里元素的最大位数
     * calculate the biggest digit
     * @param array array
     * @return int
     */
    private static int maxBin(int[] array) {
        int maxLen = 0;
        for (int anArray : array) {
            int size = Integer.toString(anArray).trim().length();
            maxLen = size > maxLen ? size : maxLen;
        }
        return maxLen;
    }

}
