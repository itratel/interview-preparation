package com.whd.interview.preparation.algorithm.sort;

import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.PrintUtil.printArray;

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
     * 得到一个中间数的索引，中间数的左边都比他小，右边的都比他大
     * @param array array
     * @param low low
     * @param high high
     * @return int
     */
    private static int partition(int[] array, int low, int high){
        //将数组下标为low的元素作为基准数（通常是第一个数）
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
        //将中间数左边的无序数组在进行递归操作
        sort(array, low,index - 1);
        //将中间数右边的无序数组在进行递归操作
        sort(array,index + 1, high);
    }

}
