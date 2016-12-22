package com.rs.ds.misc;

import java.util.Scanner;

/**
 * Created by ravisharma on 12/21/2016.
 */
public class HackonacciMatrixRotations {

    final static long[] SEVEN = {1,1,0,1,0,0,1};

    public static void main(String[] args){

        Scanner in = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("misc/HackonacciMatrixRotations-Input.txt"));
        int n = in.nextInt();
        int q = in.nextInt();
        boolean[] matrix = new boolean[n*n];
        for(int i = 0 ; i < n ; i++) {
            for (int j = i; j < n; j++) {
                matrix[i * n + j] = hackonacci((long) (Math.pow((i + 1) * (j + 1), 2)));
                matrix[j * n + i] = matrix[i * n + j];
            }
        }
        int cnt90 = 0, cnt180 = 0, cnt270 = 0;
        for(int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (cell(matrix , n, i, j) != cell90(matrix,n,i, j)) { cnt90 += 1; }
                if (cell(matrix,n,i, j) != cell180(matrix,n,i, j)) { cnt180 += 1; }
                if (cell(matrix,n,i, j) != cell270(matrix,n,i, j)) { cnt270 += 1; }
            }
        }
       for(int a0 = 0; a0 < q; a0++){
            int angle = in.nextInt() % 360;
            switch(angle){
                case 90:
                    System.out.println(cnt90);
                    break;
                case 180:
                    System.out.println(cnt180);
                    break;
                case 270:
                    System.out.println(cnt270);
                    break;
                case 0:
                    System.out.println(0);
                    break;
            }
        }
    }

    static boolean cell(boolean[] matrix , int n , int i , int j){
        return matrix[i * n + j];
    }

    static boolean cell90(boolean[] matrix , int n , int i , int j){
        return matrix[i + (n - 1 - j) * n];
    }

    static boolean cell180(boolean[] matrix , int n , int i , int j){
        return matrix[(n - 1 - i) * n + (n - 1 - j)];
    }

    static boolean cell270(boolean[] matrix , int n , int i , int j){
        return matrix[(n - 1 - i) + j * n];
    }

    static boolean hackonacci(long n ){
        return SEVEN[(int) (n % 7)] == 1;
    }

    static void printMatrix(boolean[] matrix , int n){
        for(int i = 0 ; i < n*n ; i++ ){
            System.out.print(matrix[i] + " ");
            if( (i+1) % n == 0)
                System.out.println();
        }
    }
}
