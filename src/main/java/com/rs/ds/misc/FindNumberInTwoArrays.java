package com.rs.ds.misc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ravisharma on 10/14/2016.
 */
public class FindNumberInTwoArrays {

    public static void main(String[] args){

        //  Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("misc/FindNumberInTwoArrays-Input.txt"));
        List<Set<Integer>> listSet = new ArrayList<>();
        while(sc.hasNext()){
            String line = sc.nextLine();
            Set<Integer> set = Arrays.stream(line.split(",")).map(s-> Integer.valueOf(s.trim())).collect(Collectors.toSet());
            listSet.add(set);
        }
        HashMap<Integer , Integer> tracker = new HashMap<>();
        for(Set<Integer> set : listSet){
           set.stream().forEach(
                   e -> {
                       if(tracker.containsKey(e)){
                           int counter = tracker.get(e).intValue() + 1;
                           tracker.put(e , counter);
                       } else {
                           tracker.put(e , 1);
                       }
                   }
           );
        }

        tracker.keySet().stream().filter( i -> tracker.get(i) == 2).forEach( i -> System.out.print(i + " "));
    }
}
