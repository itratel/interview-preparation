package com.whd.interview.preparation.algorithm.linkedlist.base;

/**
 * <p>ListNode<p>
 * 链表的节点
 * @author whd.java@gmail.com
 * @date 2020/11/15 17:45
 */
public class ListNode {

    /***
     * 具体值
     */
    public int val;

    /***
     * 上一个节点
     */
    public ListNode prev;

    /***
     * 下一个节点
     */
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}