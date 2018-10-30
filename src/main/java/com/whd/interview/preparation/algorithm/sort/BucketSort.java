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
        int maxNum = array.length;
        for (int anArray : array) {
            maxNum = Math.max(maxNum, anArray);
        }
        int[] newArray = new int[maxNum + 1];
        for (int ele : array) {
            newArray[ele] = ele;
        }
        int k = 0;
        for (int sort : newArray) {
            if (sort > 0) {
                array[k++] = sort;
            }
        }
        return array;
    }
}
