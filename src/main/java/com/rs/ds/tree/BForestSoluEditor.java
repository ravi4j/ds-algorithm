package com.rs.ds.tree;

import org.junit.Test;

import java.util.*;

/**
 * Created by ravisharma on 11/15/2016.
 */
public class BForestSoluEditor {


    static class Tree {

        Node[] c;
        int[] a;
        Map<Integer , List<Integer>> v ;
        long sum  , mini ; int tim;
        Map<Long , Integer> mi , ma , mi1 , ma1;
         public Tree(int n){
            this.c = new Node[n+1];
            this.v = new HashMap<>();
            this.a = new int[n+1];
            for(int i = 1 ; i <= n ; i++ ) {
                v.put(i , new ArrayList<>(n));
                a[i] = 0;
                c[i] = new Node();
            }
            mi = new HashMap<>(n);
            ma = new HashMap<>(n);
            mi1 = new HashMap<>(n);
            ma1 = new HashMap<>(n);
        }


        void dfs(int x, int pred)
        {
            tim++;
            c[x].u=tim;
            c[x].s=a[x];
            for (int i=0;i< v.get(x).size();i++)
                if ( v.get(x).get(i)!=pred)
                {
                    dfs(v.get(x).get(i),x);
                    c[x].s+=c[v.get(x).get(i)].s;
                }
            c[x].o=tim;
            return;
        }

        void print(){
            Arrays.asList(c).forEach(System.out::println);
           // System.out.println("Edge Map -> " + v);
        }


        void do_it(int x)
        {
            long add=3*c[x].s-sum;
            if (mi.get(c[x].s)!= ma.get(c[x].s))
                mini=Math.min(mini,add);
            else if (mi.get(c[x].s-add) != null && ( c[mi1.get(c[x].s-add)].u < c[x].u || c[mi1.get(c[x].s - add)].u> c[x].o))
                mini=Math.min(mini,add);
            else if (ma.get(c[x].s-add) != null && (c[ma1.get(c[x].s-add)].u<c[x].u || c[ma1.get(c[x].s-add)].u>c[x].o))
                mini=Math.min(mini,add);
            else  if (mi.get(2*c[x].s- add) != null)
                mini=Math.min(mini,add);
            else if (mi.get(2*c[x].s) != null)
                mini=Math.min(mini,add);
            return ;
        }

        void uradi(int x)
        {
            if ((sum-c[x].s)%2==0)
            {
                long p=(sum-c[x].s)/2;
                if (ma.get(p) != null && (c[ma1.get(p)].u>c[x].u || c[ma1.get(p)].o<c[x].u))
                    mini=Math.min(mini,p-c[x].s);
                else if (mi.get(p) != null && (c[mi1.get(p)].u>c[x].u || c[mi1.get(p)].o<c[x].u))
                    mini=Math.min(mini,p-c[x].s); else
                if (mi.get(p+c[x].s) != null && c[mi1.get(p+c[x].s)].u<c[x].u && c[mi1.get(p+c[x].s)].o>=c[x].u)
                    mini=Math.min(mini,p-c[x].s); else
                if (ma.get(p+c[x].s) != null && c[ma1.get(p+c[x].s)].u<c[x].u && c[ma1.get(p+c[x].s)].o>=c[x].u)
                    mini=Math.min(mini,p-c[x].s);

                return ;
            }
        }

        class Node {
            long s;
            int u;
            int o;

            @Override
            public String toString(){
                return "(s=" +s +",u="+u+",o=" +o+ ")" ;
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("tree/BalancedForest-Input2.txt"));
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            Tree g = new Tree(n);
            for (int i = 1; i <= n; i++) {
                g.a[i] = sc.nextInt();
                g.sum+=g.a[i];
            }
            for(int i = 1 ; i < n ; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                g.v.get(x).add(y);
                g.v.get(y).add(x);
            }
             g.dfs(1,0);
         //   g.print();

            for (int i=1;i<=n;i++)
                if (g.mi.get(g.c[i].s) == null)
                {
                    g.mi.put(g.c[i].s ,g.c[i].u);
                    g.ma.put(g.c[i].s ,g.c[i].u);
                    g.mi1.put(g.c[i].s, i);
                    g.ma1.put(g.c[i].s,i);
                } else
                {
                    if (g.mi.get(g.c[i].s) > g.c[i].u)
                    {
                        g.mi.put(g.c[i].s , g.c[i].u);
                        g.mi1.put(g.c[i].s, i);
                    }
                    if (g.ma.get(g.c[i].s) < g.c[i].u)
                    {
                       g.ma.put(g.c[i].s , g.c[i].u);
                        g.ma1.put(g.c[i].s ,i);
                    }
                }
          //  System.out.println("mi=" + g.mi);
          //  System.out.println("ma=" + g.ma);
          //  System.out.println("mi1=" + g.mi1);
           // System.out.println("ma1=" + g.ma1);
            g.mini=Long.MAX_VALUE;
            if (g.sum%2==0 && g.ma.get(g.sum/2) != null)  g.mini=g.sum/2;
           // System.out.println("mini="+g.mini);
            for (int i=1;i<=n;i++)
                if (3*g.c[i].s<= g.sum)
                    g.uradi(i);
                else if (2*g.c[i].s< g.sum)
                    g.do_it(i);

            if (g.mini==Long.MAX_VALUE)
                System.out.println("-1");
            else
                System.out.println(g.mini);
            t--;
        }
    }
}
