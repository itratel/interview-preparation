package com.whd.interview.preparation.algorithm.linkedlist;

import com.whd.interview.preparation.algorithm.linkedlist.base.ListNode;
import com.whd.interview.preparation.utils.PrintUtil;

/**
 * <p>ReverseLinkedList<p>
 * 反转链表
 * @author whd.java@gmail.com
 * @date 2020/11/15 17:45
 */
public class ReverseLinkedList {

    /***
     * 反转链表（简单难度级别）
     * <p>
     * 反转前：1->2->3->4->5->6->NULL
     * 反转后：6->5->4->3->2->1->NULL
     * </p>
     * @param head 链表
     * @return ListNode
     */
    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode current = head.next;
        prev.next = null;
        while (current != null) {
            ListNode next = current.next;
            //真正交换指向
            current.next = prev;
            //向后移动指针
            prev = current;
            current = next;
        }
        return prev;
    }

    /***
     * 反转链表（中等难度级别）
     * <p>
     * 反转前：1->2->3->4->5->6->NULL  m=2 n=4  (1 <= m < n <= length)
     * 反转后：1->4->3->2->5->6->NULL
     * </p>
     * @param head 链表
     * @return ListNode
     */
    private ListNode reverseList(ListNode head, int m, int n) {
        if (head == null || m >= n) {
            return head;
        }
        //创建一个哨兵节点
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        //重新构建链表，相当于在head前增加了一个sentinel节点
        head = sentinel;
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode prevM = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode;
        ListNode postN = nNode.next;
        for (int i = m; i < n; i++) {
            ListNode next = postN.next;
            postN.next = nNode;
            nNode = postN;
            postN = next;
        }
        //调整指向
        mNode.next = postN;
        prevM.next = nNode;
        return sentinel.next;
    }


    /***
     * 构建一个指定长度的链表
     * @param length 链表长度
     * @return ListNode
     */
    private ListNode createListNode(int length) {
        int initValue = 1;
        ListNode prev = null;
        ListNode current = new ListNode(initValue);
        ListNode head = current;
        int index = 0;
        while (index < length - 1) {
            ListNode next = new ListNode(++initValue);
            current.next = next;
            current.prev = prev;
            //移动指针
            prev = current;
            current = next;
            index++;
        }
        return head;
    }


    public static void main(String[] args) {
        ReverseLinkedList linkedList = new ReverseLinkedList();
        ListNode listNode = linkedList.createListNode(10);
        PrintUtil.printLinkedList(listNode);
        ListNode reverseListNode = linkedList.reverseList(listNode, 3, 6);
        PrintUtil.printLinkedList(reverseListNode);

    }


}
