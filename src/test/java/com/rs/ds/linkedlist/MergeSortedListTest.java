package com.rs.ds.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by ravisharma on 8/28/2016.
 */
public class MergeSortedListTest {

    private int[] arrayA;
    private  int[] arrayB;
    private Node listA = null;
    private Node listB = null;
    private MergeSortedList mergeSortedList;

    @Before
    public void setUp() {
        arrayA = IntStream.of(1,3,5,6).toArray();
        arrayB = IntStream.of(2,4,7).toArray();
        listA = arrayToLinkedList(arrayA);
        listB = arrayToLinkedList(arrayB);
        mergeSortedList = new MergeSortedList();
    }

    @Test
    public void testMergeSortedListWhenListBIsNull(){
        Node head = mergeSortedList.mergeList(listA , null);
        assertEquals(listA,head);
        assertArrayEquals(arrayA , linkedListToArray(head));

    }

    @Test
    public void testMergeSortedListWhenListAIsNull(){
        Node head = mergeSortedList.mergeList(null , listB);
        assertEquals(listB,head);
        assertArrayEquals(arrayB , linkedListToArray(head));
    }

    @Test
    public void testMergeSortedList(){
        Node head = mergeSortedList.mergeList(listA,listB);
        int[] mergedArrays = IntStream.concat(IntStream.of(arrayA),IntStream.of(arrayB)).sorted().toArray();
        assertArrayEquals(mergedArrays, linkedListToArray(head));
    }


    private int[] linkedListToArray(Node head){
        int[] array = new int[findSize(head)];
        int index = 0;
        Node current = head;
        while(current != null){
           array[index++] = current.data;
           current = current.next;
        }
        return array;
    }

    private int findSize(Node head){
        int size = 0;
        Node current = head;
        while(current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    private Node arrayToLinkedList(int[] data) {
        if(data.length == 0) return null;
        Node head = new Node();
        head.data = data[0];
        Node current = head;
        for (int i = 1; i < data.length; i++) {
            Node node = new Node();
            node.data = data[i];
            current.next = node;
            current = node;
        }
        return head;
    }
}
