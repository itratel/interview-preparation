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
        int[] array = newArrayByLength(20);
        printArray( array, "Before sort: ");
        int[] sorts = sort(array, 0, array.length - 1);
        printArray(sorts, "After sort: ");
    }


    /***
     * QuickSort
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array,int low, int high){
        int left = low;
        int right = high;
        //用数组的第一个记录作为分区元素
        int temp = array[left];
        // 从左右两边交替扫描，直到left = right
        while(left != right){
            //从右向左扫描，找第一个码值小于temp的记录，并交换到temp
            while(left<right&&array[right]>=temp){
                --right;
            }
            array[left]=array[right];
            //从左向右扫描，找第一个码值大于temp的记录，并交换到右边
            while(left<right&&array[left]<=temp){
                ++left;
            }
            array[right]=array[left];
        }
        //基准元素归位
        array[right]=temp;
        sort(array,low,left-1);
        sort(array,left+1,high);
        return array;
    }

}
