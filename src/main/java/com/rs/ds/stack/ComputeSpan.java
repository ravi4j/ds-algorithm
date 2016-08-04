package com.rs.ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ravisharma on 8/4/2016.
 */
public class ComputeSpan {

    private Deque<Integer> stack;

    public ComputeSpan(){
       this.stack = new ArrayDeque<>();
    }

    public int[] computeSpan(int[] prices){
        int n = prices.length;
        int[] spans = new int[n];
        for(int i = 0 ; i < n ; i++){
            boolean done = false;
            int height = 0;
            while (!(stack.isEmpty() || done)){
                if(prices[i] >= prices[stack.peek()]) {
                    stack.pop();
                } else {
                    done = true;
                }
            }
            if(!stack.isEmpty())
                height =  stack.peek();
            else
                height = -1;

            spans[i] = i - height;
            stack.push(i);
        }
        return spans;
    }

}
