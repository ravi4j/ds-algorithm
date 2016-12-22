package com.rs.ds.queue;

import java.util.*;

/**
 * Created by ravisharma on 11/28/2016.
 */
public class CastleOnTheGrid_Soln {

    public static void main(String[] args) {
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/CastleOnTheGrid-Input.txt"));
        int n = sc.nextInt();
        char[][] maze = new char[n][n];
        String row = null;
        for (int i = 0; i < n; i++) {
            row = sc.next();
            for (int j = 0; j < n; j++) {
                maze[i][j] = row.charAt(j);
            }
        }
        int x1 = sc.nextInt() , y1 = sc.nextInt();
        int x2 = sc.nextInt() , y2 = sc.nextInt();
        printMaze(maze, n);
        List<Integer>[] g = new  List[100002];
        for(int i = 0 ; i <100002 ; i++)
            g[i] = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
        {
            for(int j = 0 ; j < n; j++)
            {
                if(maze[i][j] == '.')
                {
                    int cur = i*n + j;
                    int index = 0;
                    for(int k = i-1; k >= 0; k--)
                    {
                        if(maze[k][j] == '.')
                        {
                            int now = k*n + j;
                            g[cur].add(now);
                        }
                        else
                            break;
                    }
                    for(int k = i+1; k < n; k++)
                    {
                        if(maze[k][j] == '.')
                        {
                            int now = k*n + j;
                            g[cur].add(now);
                        }
                        else
                            break;
                    }
                    for(int k = j-1; k >= 0; k--)
                    {
                        if(maze[i][k] == '.')
                        {
                            int now = i*n + k;
                            g[cur].add(now);
                        }
                        else
                            break;
                    }
                    for(int k = j+1; k < n; k++)
                    {
                        if(maze[i][k] == '.')
                        {
                            int now = i*n + k;
                            g[cur].add(now);
                        }
                        else
                            break;
                    }
                }
            }
        }
        printGraph(g , 100002);
        int start_pos = x1*n + y1;
        boolean[] done = new boolean[10001] ;
        int[] dis = new int[10001];
        Deque<Integer> q = new ArrayDeque<>(10001);
        q.push(start_pos);
        done[start_pos] = true;
        while(!q.isEmpty())
        {
            int now = q.poll();
            for(int i = 0 ; i < g[now].size(); i++)
            {
                int nxt =  g[now].get(i);
                if(!done[nxt])
                {
                    done[nxt] = true;
                    dis[nxt] = dis[now]+1;
                    q.add(nxt);
                }
            }
        }
        System.out.println(dis[x2*n + y2]);

    }
    public static void printMaze(char[][] maze, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }

    public static void printGraph(List<Integer>[] graph, int n) {
        for (int i = 0; i < n; i++) {
                System.out.println(graph[i] + " ");
        }
    }
}
