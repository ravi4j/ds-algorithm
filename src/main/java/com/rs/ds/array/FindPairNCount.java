package com.rs.ds.array;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by ravisharma on 10/21/2016.
 */
//Sherlock and Pairs
public class FindPairNCount {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

       // Scanner sc =  new Scanner(System.in);
        Scanner sc =  new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("array/FindPairNCount-Input.txt"));
        int t = sc.nextInt();
        while(t > 0){
            int size = sc.nextInt();
            int[] array = new int[size];
            for(int i = 0 ; i < size ; i++){
                array[i] = sc.nextInt();
            }
            BigInteger pair = findPair(array , size);
            System.out.println(pair);
            t--;
        }
    }

    public static BigInteger findPair(int[] array , int size){
        Map<Integer , Integer> fMap = new HashMap<>();
        for(int i = 0 ; i < size ; i++){
            if(fMap.containsKey(array[i])){
                int freq = fMap.get(array[i]);
                fMap.put(array[i] , Integer.valueOf(freq + 1));
            } else{
                fMap.put(array[i] , 1);
            }
        }
        BigInteger count = BigInteger.ZERO;
        for(Integer key : fMap.keySet()){
            if(fMap.get(key).intValue() != 1){
                long pairs = countPair(fMap.get(key).intValue());
                count =  count.add(BigInteger.valueOf(pairs));
            }
        }
        return count;
    }

    public static long countPair(int freq){
        return (long) freq * (freq - 1);
    }

}
