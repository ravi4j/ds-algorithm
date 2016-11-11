package com.rs.ds.misc;

/**
 * Created by ravisharma on 10/14/2016.
 */
public class CovertIntToString {

    public static void main(String[] args){

        int input = 123456789;
        String str = convertIntToString(input);
        System.out.println(str);
        int number = convertStringToInt(str);
        System.out.println(number);
    }

    public static String convertIntToString(int number){
        StringBuilder builder = new StringBuilder();
        while(number!=0){
            int digit = number%10;
            builder.append(digit);
            number = number/10;
        }
        return builder.reverse().toString();
    }

    public static int convertStringToInt(String str){
        int number = 0;
        char[] chars = str.toCharArray();
        for(int i = 0 ; i < chars.length ; i++){
            number*=10 ;
            number += chars[i] - '0';
        }
        return number;
    }
}
