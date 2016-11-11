package com.rs.ds.misc;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by ravisharma on 10/14/2016.
 */
public class AnagramSolution {

    public static void main(String[] args){

        //  Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("misc/Anagram-Input.txt"));
        NavigableSet<String> input = new ConcurrentSkipListSet<>();
        while(sc.hasNext()){
            input.add(sc.nextLine());
        }
        List<SortedSet<String>> anagramList = new ArrayList<>();
        while (!input.isEmpty()) {
            NavigableSet<String> list = new TreeSet<>();
            String word = input.pollFirst();
            list.add(word);
            Iterator<String> iterator = input.iterator();
            while(iterator.hasNext()){
                String word1 = iterator.next();
                if(isAnagram(word , word1)){
                    list.add(word1);
                    input.remove(word1);
                }
            }
            anagramList.add(list);
        }
        anagramList.stream().forEach(
                l -> System.out.println(l));
    }


    public static boolean isAnagram(final String word1 , final String word2){
        char[] chars1 = word1.trim().replace(" " ,"").toLowerCase().toCharArray();
        char[] chars2 = word2.trim().replace(" " ,"").toLowerCase().toCharArray();
        if(chars1.length != chars2.length)
            return  false;
       Arrays.sort(chars1);
       Arrays.sort(chars2);
       return String.valueOf(chars1).equalsIgnoreCase(String.valueOf(chars2));
    }


}
