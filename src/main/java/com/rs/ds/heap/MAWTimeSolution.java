package com.rs.ds.heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by ravisharma on 11/20/2016.
 */
public class MAWTimeSolution {

   static class Customer {
        int a;
        int c;

       public Customer(int a , int c){
           this.a = a;
           this.c = c;
       }
       @Override
       public String toString(){
           return "(" + a + " , " + c + ")";
       }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("heap/MAWTimeSolution-Input.txt"));
        int n = sc.nextInt();
        Queue<Customer> arrivals = new PriorityQueue<>(n , (c1 , c2)-> c1.a - c2.a);
        int a , c;
        while(n > 0){
            a = sc.nextInt();
            c = sc.nextInt();
            arrivals.add(new Customer(a,c));
            n--;
        }
        System.out.println("Customers --> "+ arrivals);
        System.out.println("first customer-->" + arrivals.peek());
    }
}
