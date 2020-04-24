package com.whd.interview.preparation.algorithm.sort;



import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.ArrayUtil.printArray;

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
            newArray[ele]++;
        }
        for (int i = 0, k = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i]; j++) {
                array[k++] = i;
            }
        }
        return array;
    }
}
