package com.whd.interview.preparation.algorithm.linkedlist;

import com.whd.interview.preparation.algorithm.linkedlist.base.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>CopyListRandomPointer<p>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/15 21:46
 * @since
 */
public class CopyListRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node newHead = head;
        while (newHead != null) {
            if (!map.containsKey(newHead)) {
                Node copyNode = new Node(newHead.val);
                map.put(newHead, copyNode);
            }
            if (newHead.random != null) {
                Node random = newHead.random;
                if (!map.containsKey(random)) {
                    Node copyRandom = new Node(random.val);
                    map.put(random, copyRandom);
                }
                map.get(newHead).random = map.get(random);
            }
            newHead = newHead.next;
        }
        newHead = head;
        while (newHead != null) {
            Node next = newHead.next;
            map.get(newHead).next = map.get(next);
            newHead = newHead.next;
        }
        return map.get(head);
    }
}
