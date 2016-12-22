package com.rs.ds.heap;

import java.util.*;

/**
 * Created by ravisharma on 11/17/2016.
 */
public class RunningMedian {



    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("heap/Running Median-Input.txt"));
        int n = sc.nextInt();
        int capacity = n / 2 + 1;
        Queue<Integer> r = new PriorityQueue<>(capacity);
        Queue<Integer> l = new PriorityQueue<>(capacity,(a,b)->  b-a);
        double median = 0.0;
        int i = 1;
        while ( i  <= n){
            median = getMedian(sc.nextInt() , median , l , r );
            System.out.println(median);
            i++;
        }
    }


    public static double getMedian(int e , double m , Queue<Integer> l , Queue<Integer> r){

        int sign = adjSize(l.size() , r.size());
        switch (sign){
            case 1:
                if( e < m) {
                    if(!l.isEmpty())
                        r.add(l.poll());
                    l.add(e);
                } else {
                    r.add(e);
                }
                m = average(l.peek().intValue() , r.peek().intValue());
                break;
            case 0:
                if(e < m){
                    l.add(e);
                    m = l.peek();
                }else {
                   r.add(e);
                    m = r.peek();
                }
                break;
            case -1:
                if(e < m){
                    l.add(e);
                }else {
                    if(!r.isEmpty())
                        l.add(r.poll());
                    r.add(e);
                }
                m = average(l.peek().intValue() , r.peek().intValue());
                break;
        }
        return m;
    }


    public static int adjSize(int a , int b){
        if(a == b) return 0;
        return a < b ? -1 : 1;
    }

    public static double average(int a , int b){
        return (a + b) / 2.0;
    }

}
