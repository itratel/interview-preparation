package com.whd.interview.preparation.sortalgorithm;

import static com.whd.interview.preparation.sortalgorithm.ArrayUtils.newArrayByLength;
import static com.whd.interview.preparation.sortalgorithm.ArrayUtils.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 17:24
 * @apiNote QuickSort
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        sort(array, 0, array.length - 1);
        printArray(array, "After sort: ");
    }

    /***
     *
     * @param array array
     * @param low low
     * @param high high
     * @return int
     */
    private static int partition(int[] array, int low, int high){
        //固定的切分方式
        int key = array[low];
        while(low < high){
            while(array[high] >= key && high > low){
                high--;
            }
            array[low] = array[high];
            while(array[low] <= key && high > low){
                low++;
            }
            array[high] = array[low];
        }
        array[high] = key;
        return high;
    }

    /***
     * QuickSort
     * @param array
     * @param low
     * @param high
     */
    private static void sort(int[] array,int low ,int high){
        if(low >= high){
            return;
        }
        int index = partition(array, low, high);
        System.out.println("index = " + index);
        sort(array, low,index - 1);
        sort(array,index + 1, high);
    }

}
