package com.rs.ds.misc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by ravisharma on 10/21/2016.
 */
public class GeneratePrime {

    public static void main(String[] args){

        //  Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("misc/GeneratePrime-Input.txt"));
        int t = sc.nextInt();
        while(t > 0 ) {
            long n = sc.nextLong();
            List<Long> factors = findFactors(n);
            System.out.println(factors.size());
            t--;
        }
        Long[] N = {1L ,2L , 3L , 4L};
        BigInteger routes = Stream.of(N).map( i -> {return BigInteger.valueOf(i);}).reduce((b1 , b2) -> b1.multiply(b2)).orElse(BigInteger.ONE);
    }

    public static List<Long> findFactors(long q){
        List<Long> factors = new ArrayList<>();
        long p = 0;
        long mul = 1;
        long count = 1;
        while( mul <= q ) {
           p = nthPrimeNumber(count++);
           mul = mul * p;
           if(mul <= q)
                factors.add(p);
        }

        return factors;
    }

    public static long nthPrimeNumber(long limit) {
        return LongStream.iterate(2, i -> i + 1)
                .filter(i -> isPrime(i))
                .limit(limit)
                .max()
                .orElse(0L);
    }

    public static boolean isPrime(long n) {
        if(n  < 2) return false;

        if (n == 2) return true;

        if(n % 2 == 0 ) return false;
        for(int i = 3 ; i  <= Math.sqrt(n) ; i+=2 ){
            if(n % i == 0)
                return false;
        }
        return true;
    }


}
