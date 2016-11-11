package com.rs.ds.array;

import java.util.Scanner;

/**
 * Created by ravisharma on 10/14/2016.
 */
public class DeltaEncoding {


    public static void main(String[] args) {

        //  Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("array/DeltaEncoding-Input.txt"));
        int prev = sc.nextInt();
        System.out.print(prev + " ");
        int next = 0;
        while (sc.hasNext()) {
            next = sc.nextInt();
            if (Math.abs(next - prev) > 128) {
                System.out.print("-128 " + (next - prev) + " ");
            }
            prev = next;
        }

    }
}
