package com.rs.ds.array;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by ravisharma on 10/14/2016.
 * Booking.com
 */
public class FindSumInArray {

    public static void main(String[] args){

      //  Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner( Thread.currentThread().getContextClassLoader().getResourceAsStream("array/FindSumInArray-Input.txt"));
        int sum = sc.nextInt();
        int size = sc.nextInt();
        int[] array = new int[size];
        for(int i = 0 ; i < size ; i++){
            array[i] = sc.nextInt();
        }
       // int result = findSum(array , sum);
        Set<Integer> result = findUniquePairOfSum(array,sum);
        System.out.println(result);

    }

    public static int findSum(int[] array , int sum){
        HashMap<Integer , Integer> map  = new HashMap<>();
        for(int i=0 ; i < array.length ; i++){
            map.put(Integer.valueOf(array[i]) , Integer.valueOf(i));
        }
        for(int i =0 ; i < array.length ; i++){
            int diff = sum - array[i];
            if(map.get(Integer.valueOf(diff)) != null){
                int mapIndex = map.get(Integer.valueOf(diff)).intValue();
                if(mapIndex  != i){
                    System.out.println( "Pair found at (" + i + " , " + mapIndex + " ) ==> " + array[i] + " , " + array[mapIndex] + " = " + sum);
                    return  1; // found
                }
            }
        }
        return 0;
    }

    public static Set<Integer> findUniquePairOfSum(int[] array , int sum){
        HashMap<Integer , Integer> map  = new HashMap<>();
        for(int i=0 ; i < array.length ; i++){
            map.put(Integer.valueOf(array[i]) , Integer.valueOf(i));
        }
        Set<Integer> pair = new HashSet<>();
        for(int i =0 ; i < array.length ; i++){
            int diff = sum - array[i];
            if(map.get(Integer.valueOf(diff)) != null){
                int mapIndex = map.get(Integer.valueOf(diff)).intValue();
                if(mapIndex  != i){
                    System.out.println( "Pair found at (" + i + " , " + mapIndex + " ) ==> " + array[i] + " , " + array[mapIndex] + " = " + sum);
                    pair.add(array[i]);
                    pair.add(array[mapIndex]);
                    //return  1; // found
                }
            }
        }
        return pair;
    }

}
