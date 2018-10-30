package com.whd.interview.preparation.sortalgorithm;

import static com.whd.interview.preparation.sortalgorithm.ArrayUtils.newArrayByLength;
import static com.whd.interview.preparation.sortalgorithm.ArrayUtils.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 22:24
 * @apiNote SelectSort
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        int[] newArray = sort(array);
        printArray(newArray, "After sort: ");
    }

    /***
     * SelectSort
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array){
        int length = array.length;
        for(int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < length; j++){
                // 选最小的记录
                if(array[j] < array[minIndex]){
                    //记下目前找到的最小值所在的位置
                    minIndex = j;
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }
}
