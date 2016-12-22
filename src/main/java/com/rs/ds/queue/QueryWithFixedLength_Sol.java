package com.rs.ds.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by ravisharma on 11/27/2016.
 */
public class QueryWithFixedLength_Sol {

    public static void main(String[] args) {

        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/QueryWithFixedLength-Input.txt"));
        int n = sc.nextInt(), q = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        while(q > 0){
            Deque<Integer> deque = new ArrayDeque<>();
            int d = sc.nextInt();
            for (int i = 0; i < d; i++) {
                while (!deque.isEmpty() && a[i] >= a[deque.getLast()])
                    deque.pollLast();
                deque.addLast(i);
            }
            PriorityQueue<Integer> min = new PriorityQueue<>();
            for (int i = d; i < n; i++) {
                min.add(a[deque.peekFirst()]);
                while (!deque.isEmpty() && a[i] >= a[deque.peekLast()])
                    deque.pollLast();
                while (!deque.isEmpty() && deque.peek() <= i-d)
                    deque.pop();
                deque.addLast(i);
            }
            min.add(a[deque.getFirst()]);
            System.out.println(min.peek());
            min.clear();
            q--;
        }
    }
}
