package com.rs.ds.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by ravisharma on 11/26/2016.
 */
public class TruckTour {

    static class Node {
        int gas;
        int next;
        public Node(int gas , int next){
            this.gas = gas;
            this.next = next;
        }

        @Override
        public String toString(){
            return "(" + gas + ","+ next + ")";
        }

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/TruckTour-Input.txt"));
        int N = sc.nextInt();
        Deque<Node> deque = new ArrayDeque<>();
        int i = N;
        while(i > 0){
            deque.add(new Node(sc.nextInt() , sc.nextInt()));
         i--;
        }
        int start = 0 , passed = 0 , gas = 0;
        while (passed < N){
            Node st = deque.pop();
            gas +=st.gas;
            if (gas >= st.next) {
                passed += 1;
                gas -= st.next;
            } else {
                start += passed + 1;
                passed = 0; gas = 0;
            }
            deque.add(st);

        }
        System.out.println(start);
    }
}
