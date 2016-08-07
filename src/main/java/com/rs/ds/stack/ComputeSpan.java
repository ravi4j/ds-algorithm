package com.rs.ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ravisharma on 8/4/2016.
 */
public class ComputeSpan {

    /**
     * A Linear Time Complexity Method
     * We see that S[i] on day i can be easily computed if we know the closest day preceding i,
     * such that the price is greater than on that day than the price on day i.
     * If such a day exists, let’s call it h(i), otherwise, we define h(i) = -1.
     * The span is now computed as S[i] = i – h(i). See the following diagram.
     * @param prices - Arrays of stock prices
     * @return Array containing span of ith price at Span[i]
     */
    public int[] computeSpan(int[] prices){
        Deque<Integer> stack = new ArrayDeque<>();
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
