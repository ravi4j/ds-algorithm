package com.rs.ds.linkedlist;

/**
 * Created by ravisharma on 8/28/2016.
 */
public class MergeSortedList {


    public Node mergeList(Node headA, Node headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;
        Node result = null;
        if (headA.data < headB.data) {
            result = headA;
            result.next = mergeList(headA.next, headB);
        } else {
            result = headB;
            result.next = mergeList(headA, headB.next);
        }
        return result;
    }
}


class Node {
    public int data;
    public Node next;
}


