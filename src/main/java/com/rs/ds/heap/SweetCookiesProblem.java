package com.rs.ds.heap;

import java.util.*;

/**
 * Created by ravisharma on 10/3/2016.
 */
public class SweetCookiesProblem {

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner( Thread.currentThread().getContextClassLoader().getResourceAsStream("heap/SweetCookiesProblem-Input2.txt"));
        int noOFCookies = sc.nextInt();
        int limit = sc.nextInt();
        PriorityQueue<Integer> heap = new PriorityQueue<>((x,y) -> x-y);
        for(int i = 0 ; i < noOFCookies;i++){
            heap.add(sc.nextInt());
        }
        sc.close();
        int count = mixedSweetness(heap,limit);
        System.out.println(count);
    }

    private static int mixedSweetness(PriorityQueue<Integer> heap , int limit){
        int count = 0;
        while(!heap.isEmpty()){
            if(heap.peek() < limit) {
                if(heap.size() >= 2) {
                    heap.add(calculateSweetness(heap.poll(), heap.poll()));
                    count++;
                } else {
                    return -1;
                }
            } else {
                return count;
            }
        }
        return -1;
    }

    private static int calculateSweetness(int leastSweet1 , int leastSweet2) {
        return (1 * leastSweet1) + ( 2 * leastSweet2);
    }

}
