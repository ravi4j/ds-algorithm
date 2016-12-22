package com.rs.ds.queue;

import java.util.*;

/**
 * Created by ravisharma on 11/27/2016.
 */
public class QueryWithFixedLength {


    public static void main(String[] args){

        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/QueryWithFixedLength-Input.txt"));
        int n = sc.nextInt(), q = sc.nextInt();
       int[] a = new int[n];
        Deque<Integer> in = new ArrayDeque<>(n);
        for(int i =0;i < n ;i++) {
            a[i] = sc.nextInt();
            in.add(a[i]);
        }
        PriorityQueue<Integer> out = new PriorityQueue<>();
        while(q > 0){
            int d = sc.nextInt();
            int max = Integer.MIN_VALUE;
            for(int i = 0 ; i <= n - d ; i++) {
                for (int j = i; j < i + d; j++) {
                    max = Math.max(max, a[j]);
                }
              //  System.out.println(max);
                out.add(max);
                max = Integer.MIN_VALUE;
            }
             System.out.println(out.peek());
            out.clear();
            q--;
        }

        }

}
