package com.whd.interview.preparation.algorithm.sort;


import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.newArrayByLength;
import static com.whd.interview.preparation.algorithm.utils.ArrayUtils.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/30 16:22
 * @apiNote BucketSort
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        int[] sorts = sort(array);
        printArray(sorts, "After  sort: ");
    }

    /***
     * BucketSort
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array){
        int length = array.length;
        int maxNum = 100;
        int[] sorted = new int[maxNum + 1];
        for (int anArray : array) {
            //把数据放到对应索引的位置
            sorted[anArray] = anArray;
        }
        for (int i = 0; i < sorted.length; i++) {
            if ( sorted[i] > 0){

            }
        }
        return sorted;
    }
}
