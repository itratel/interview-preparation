package com.whd.interview.preparation.algorithm.sort;

import static com.whd.interview.preparation.utils.ArrayUtil.newArrayByLength;
import static com.whd.interview.preparation.utils.PrintUtil.printArray;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/30 10:04
 * @apiNote HeapSort
 */
public class HeapSort {

    private static final int TWO_CHILD = 2;

    public static void main(String[] args) {
        int[] array = newArrayByLength(10);
        printArray( array, "Before sort: ");
        int[] newArray = sort(array);
        printArray(newArray, "After sort: ");
    }


    /***
     * (最大)堆的向下调整算法注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *  其中，N为数组下标索引值，如数组中第1个数对应的N为0
     * @param array array to sort
     * @param start 被下调节点的起始位置(一般为0，表示从第1个开始)
     * @param end 截至范围(一般为数组中最后一个元素的索引)
     */
    private static void maxHeapDown(int[] array, int start, int end) {
        //当前(current)节点的位置
        int cur = start;
        //左(left)孩子的位置
        int left = TWO_CHILD * cur + 1;
        //当前(current)节点的大小
        int tmp = array[cur];

        for (; left <= end; cur = left, left = TWO_CHILD * left + 1) {
            // "left"是左孩子，"left+1"是右孩子
            if ( left < end && array[left] < array[left + 1]){
                // 左右两孩子中选择较大者，即m_heap[left+1]
                left++;
            }
            if (tmp >= array[left]) {
                // 调整结束
                break;
            } else {
                // 交换值
                array[cur] = array[left];
                array[left]= tmp;
            }
        }
    }


    /***
     * HeapSort(from small to big)
     * @param array array to sort
     * @return array
     */
    private static int[] sort(int[] array) {
        int i, length = array.length;
        // 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (i = length / TWO_CHILD - 1; i >= 0; i--){
            maxHeapDown(array, i, length-1);
        }

        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = length - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
            // 即，保证a[i-1]是a[0...i-1]中的最大值。
            maxHeapDown(array, 0, i - 1);
        }
        return array;
    }

}
