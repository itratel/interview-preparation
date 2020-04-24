package com.whd.interview.preparation.algorithm.sort;

import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.ArrayUtil.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/30 11:53
 * @apiNote MergeSort
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        sort(array, 0, array.length -1  );
        printArray(array, "After  sort: ");
    }

    /***
     * MergeSort
     * @param array array ot sort
     * @param low low
     * @param high high
     */
    private static void sort(int[] array, int low, int high){
        if (low >= high){
            return;
        }
        int middle = (low + high) >> 1;
        //左边归并排序，使得右子序列有序
        sort(array, low, middle);
        //右边归并排序，使得右子序列有序
        sort(array, middle + 1, high);
        //将两个有序子数组合并操作
        mergeSort(array, low, middle, high);
    }


    /***
     * merge two array to array
     * @param array array
     * @param start start
     * @param middle middle
     * @param end end
     */
    private static void merge(int[] array, int start, int middle, int end) {
        // 定义一个临时数组，用来存储排序后的结果
        int[] temp = new int[end + 1];
        // 临时数组的索引
        int low = start, left = start, center = middle + 1;
        // 取出最小值放入临时数组中
        while (start <= middle && center <= end) {
            temp[low++] = array[start] > array[center] ? array[center++] : array[start++];
        }
        // 把左边剩余的数移入数组
        while (start <= middle) {
            temp[low++] = array[start++];
        }
        // 把右边边剩余的数移入数组
        while (center <= end) {
            temp[low++] = array[center++];
        }
        // 将临时数组中的值copy到原数组中
        System.arraycopy(temp, left, array, left, end + 1 - left);
    }


    /***
     * merge two array to array
     * @param array array
     * @param low low
     * @param middle middle
     * @param high high
     */
    private static void mergeSort(int[] array, int low, int middle, int high){
        //将有序序列array[low...mid]和有序序列array[mid+1...upper]合并成一个有序序列
        //将两个有序子序列合并，存放于一个临时数组中，合并完成后再复制回原序列数组中
        int[] temp = new int[high - low + 1];
        int i = low, j = middle + 1, k = 0;
        while(i <= middle && j <= high){
            //将两个子序列归并
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        //将两个子序列尚未处理完的部分复制到temp中
        while(i <= middle){
            temp[k++] = array[i++];
        }
        while(j <= high){
            temp[k++] = array[j++];
        }

        //归并完成，将temp中的元素复制回array中
        for(k = 0, i = low; i <= high; k++, i++){
            array[i] = temp[k];
        }
    }
}
