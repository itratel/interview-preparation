package com.whd.interview.preparation.algorithm.sort;

import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.PrintUtil.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:20
 * @apiNote BubbleSort
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = newArrayByLength(20);
        printArray( array, "Before sort: ");
        int[] sorts = sort(array);
        printArray(sorts, "After sort: ");
    }

    /***
     * BubbleSort
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array){
        boolean flag = true;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = false;
                }
            }
            if (flag){
                flag = true;
            }
        }
        return array;
    }
}
