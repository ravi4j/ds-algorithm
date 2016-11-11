package com.rs.ds.tree;

import javafx.util.Pair;
import sun.security.util.Length;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ravisharma on 11/3/2016.
 */
public class SquareTenTree {

    static boolean borrow_flag;

    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("tree/SquareTenTree-Input.txt"));
        String L = sc.next();
        String R = sc.next();
        final int N = 1 << 8;
        System.out.println(N);
        char[] ch = new char[N - L.length()];
        Arrays.fill(ch , '0');
        L = new StringBuilder(String.valueOf(ch)).append(L).reverse().toString();
        ch = new char[N - R.length()];
        Arrays.fill(ch , '0');
        R = new StringBuilder(String.valueOf(ch)).append(R).reverse().toString();
        System.out.println(L);
        System.out.println(R);
        List<Pair<Integer , String>> ans_l = new ArrayList<>(), ans_r = new ArrayList<>();
        for (int d = 0; d <= 7; d++) {
            char[] prev_L = L.toCharArray();
            char[] prev_R = R.toCharArray();
            move_next(prev_L, power2(d + 1));
            move_prev(prev_R, power2(d + 1));
            String tmp = String.valueOf(subtract(R.toCharArray(), L.toCharArray()));
            if (borrow_flag || trim_leadzero(tmp.toCharArray()) == "0") {
                String num = String.valueOf(trim_leadzero(subtract(prev_R, prev_L)).substring(power2(d)));
                if (num != "0") ans_l.add(new Pair(d, num));
                break;
            } else {
                String num_l = String.valueOf(trim_leadzero(subtract(L.toCharArray(), prev_L)).substring(power2(d)));
                String num_r = String.valueOf(trim_leadzero(subtract(prev_R, R.toCharArray())).substring(power2(d)));
                if (num_l != "0") ans_l.add(new Pair(d, num_l));
                if (num_r != "0") ans_r.add(new Pair(d, num_r));
            }
        }

        for(Pair pair : ans_l)
            System.out.println(pair.getKey() + " " + pair.getValue());

    }

    static String trim_leadzero(char[] a) {
        int i = a.length - 1;
        while (i >= 0 && a[i] == '0') i--;
        if (i == -1) return "0";
        a = String.valueOf(a).substring(0, i + 1).toCharArray();
        StringBuilder sb = new StringBuilder(String.valueOf(a)).reverse();
        return sb.toString();
    }

    static void move_next(char[] s, int pos) {
        boolean any = false;
        for (int i = 0; i < pos; i++) {
            any |= s[i] != '0';
            s[i] = '0';
        }
        if (any) increment(s, pos);
    }

    static void increment(char[] s, int k) {
        for (int i = k; i < s.length; i++) {
            s[i]++;
            if (s[i] - '0' == 10) s[i] -= 10;
            else break;
        }

    }

   static void decrement(char[] s, int k) {
        for (int i = k; i < s.length; i++) {
            s[i]--;
            if (s[i] - '0' < 0) s[i] += 10;
            else break;
        }
    }

   static void move_prev(char[] s, int pos) {
        for (int i = 0; i < pos; i++) s[i] = '0';
    }

   static  int power2(int d) {
        if (d == 0) return 0;
        return 1 << (d - 1);
    }

    static char[] subtract(char[] a, char[] b) {
        int borrow = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] -= (b[i] - '0') + borrow;
            borrow = (a[i] < '0') ? 1 : 0;
            if (a[i] < '0') a[i] += 10;
        }
        borrow_flag = borrow == 1 ? true : false;
        return a;
    }
}
