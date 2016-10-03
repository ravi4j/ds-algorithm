package com.rs.ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

/**
 * Created by ravisharma on 8/30/2016.
 */
public class LargestRectangle {

    public static void main(String[] args) {
        int n = 5;
        int[] heights = IntStream.of(1,2,3,4,5).toArray();
        int max =  maxArea(heights , n);
        System.out.println(max);
    }

    public static int maxArea(int[] heights , int n){
        int maxArea = 0;
        int areaWithTop = 0;
        int top;
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while(i < n){
            if(stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i++);
            } else {
               top = stack.pop();
               areaWithTop = heights[top] * (stack.isEmpty() ? i : i - top);
               if(maxArea < areaWithTop){
                maxArea = areaWithTop;
               }
            }
        }
        while(!stack.isEmpty()){
            top = stack.pop();
            areaWithTop = heights[top] * (stack.isEmpty() ? i : i - top);
            if(maxArea < areaWithTop){
                maxArea = areaWithTop;
            }
        }
        return maxArea;
    }

}
