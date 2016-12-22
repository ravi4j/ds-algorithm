package com.rs.ds.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by ravisharma on 11/21/2016.
 */
public class QueueUsing2Stacks {

    public static void main(String[] args){

        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/QueueUsing2Stacks-Input.txt"));
        int n = sc.nextInt();
        Deque<Integer> inbox = new ArrayDeque<>();
        Deque<Integer> outbox = new ArrayDeque<>();
        while( n > 0){
            int op = sc.nextInt();
            switch(op){
                case 1:
                    int ele = sc.nextInt();
                    inbox.push(ele);
                    break;
                case 2:
                    if(outbox.isEmpty()) {
                        while (!inbox.isEmpty()) {
                            outbox.push(inbox.pop());
                        }
                    }
                    if(!outbox.isEmpty())
                      outbox.pop();
                    break;
                case 3:
                    if(outbox.isEmpty()) {
                        while (!inbox.isEmpty()) {
                            outbox.push(inbox.pop());
                        }
                    }
                    if(!outbox.isEmpty())
                        System.out.println(outbox.peek());
                    break;
            }
            n--;
        }
    }
}

