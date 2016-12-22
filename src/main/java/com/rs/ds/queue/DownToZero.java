package com.rs.ds.queue;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ravisharma on 11/25/2016.
 */
public class DownToZero {

    static Queue<Node> queue = new ArrayDeque<>();

    static class Node {
        int n;
        int dist;

        public Node(int n , int dist){
          this.n = n;
          this.dist = dist;
        }

        @Override
        public String toString(){
            return "(" + n + "," +dist + ")";
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/DownToZero-Input.txt"));
        int t = sc.nextInt();
        while(t > 0) {
            int n = sc.nextInt();
            int steps = reduce(n);
            System.out.println(steps);
            queue.clear();
            t--;
        }

    }


    public static int reduce(int n){

       // if( n== 0 ) return 0;
        Node node = new Node(n , 0);
        queue.add(node);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(curr.n == 0) {
                return curr.dist;
            }
            Deque<Integer> factors = getMaxFactors(curr.n);
            for(Integer factor : factors){
                Node nFactor = new Node(factor,curr.dist + 1);
                queue.add(nFactor);
            }
            queue.add(new Node(curr.n - 1 , curr.dist+ 1));
          //  System.out.println(queue);
        }

        return Integer.MAX_VALUE;
    }

    public static Deque<Integer> getMaxFactors(int n){
        Deque<Integer> deque = new ArrayDeque<>();
        int sqrtN = (int) Math.sqrt(n);
        for(int i = 2 ; i <= sqrtN ; i++ ){
            if( n % i == 0){
                int j = n / i;
                deque.add(Math.max(i , j));
            }
        }
        return deque;
    }
}
