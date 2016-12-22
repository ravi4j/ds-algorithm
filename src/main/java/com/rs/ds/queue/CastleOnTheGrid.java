package com.rs.ds.queue;

import java.util.*;

/**
 * Created by ravisharma on 11/22/2016.
 */
public class CastleOnTheGrid {

    static class Point {
        int x;
        int y;

        public Point(){
            this.x = 0;
            this.y = 0;
        }
        public Point(int x , int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(o == null) return false;
            if(this == o) return true;
            if(!(o instanceof Point)) return false;
            Point that = (Point)o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode(){
            int hashCode = 17;
            hashCode = 31 * hashCode + x;
            hashCode = 31 * hashCode + y;
            return hashCode;
        }

        @Override
        public String toString(){
            return "(" + x + "," + y +")";
        }
    }

    static class Node {
        Point p;
        int dist;

        public Node(){
            this.p = new Point();
            this.dist = 0;
        }

        public Node(Point p , int dist){
            this.p = p;
            this.dist = dist;
        }
        public Node(int x , int y , int dist){
            this.p = new Point(x,y);
            this.dist = dist;
        }
        @Override
        public String toString() {
            return p.toString() + "{" +dist+"}";
        }
    }

    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("queue/CastleOnTheGrid-Input.txt"));
        int n = sc.nextInt();
        int[][] maze = new int[n][n];
        String row = null;
        for(int i = 0 ; i < n; i++) {
            row = sc.next();
            for (int j = 0; j < n; j++)
                if (row.charAt(j) == '.')
                    maze[i][j] = 1;
                else
                    maze[i][j] = 0;
        }
        printMaze(maze,n);
        Point s = new Point(sc.nextInt() ,sc.nextInt());
        Point d = new Point(sc.nextInt() ,sc.nextInt());
        int dist = bfs(maze,s,d,n);
        System.out.println(dist);

    }

    public static int bfs(int[][] maze , Point s , Point d , int size){
        if(!validPoint(maze,s) && !validPoint(maze,d))
            return Integer.MAX_VALUE;

        boolean[][] visited = new boolean[size][size];
        List<Point> nodeQ = new ArrayList<>();
        List<Point> originQ = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        Node node = new Node(s , 0);
        visited[s.x][s.y] = true;
        queue.offer(node);
        while (!queue.isEmpty()){
            Node current = queue.peek();
            Point p = current.p;
            if(p.x == d.x && p.y == d.y) {
                System.out.println(nodeQ);
               System.out.println(originQ);
                Deque<Point> path = shortPath(s ,d, nodeQ , originQ);
               System.out.println(path);
                printPath(path , size);
                return findDistance(path);
            }
            queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int r = p.x + rowNum[i];
                int c = p.y + colNum[i];
                if(isValid(r,c,size) && maze[r][c] == 1 && !visited[r][c]){
                    visited[r][c] = true;
                    Point point = new Point(r,c);
                    Node adj = new Node(point, (current.dist + 1));
                    nodeQ.add(point);
                    originQ.add(current.p);
                    queue.offer(adj);
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int findDistance(Deque<Point> path){
        if(path == null) return 0;
        if(path.size() == 0) return 0;
        if(path.size() == 1) return 1;
        Point p1 = path.pop();
        Point p2 = path.peek();
        int dist = 1;
        Point prev = new Point(p1.x - p2.x , p1.y - p2.y);
        while(!path.isEmpty()){
            p1 = path.pop();
            if(path.peek() != null) {
                p2 = path.peek();
                Point current = new Point(p1.x - p2.x, p1.y - p2.y);
                if (!prev.equals(current))
                    dist += 1;
                prev = current;
            }
        }
        return dist;
    }

    public static void printPath(Deque<Point> path , int size){
        int[][] maze = new int[size][size];
         for(Point point : path){
             maze[point.x][point.y] = 1;
         }
         printMaze(maze,size);
    }

    public static Deque<Point> shortPath(Point source , Point end , List<Point> nodeQ , List<Point> originQ){
        Deque<Point> path = new ArrayDeque<>();
        path.push(end);
        int index = nodeQ.indexOf(end);
        Point origin = originQ.get(index);
        while(!origin.equals(source)){
            path.push(origin);
            index = nodeQ.indexOf(origin);
            origin = originQ.get(index);
        }
        path.push(source);
        return path;
    }

    public static boolean isValid(int row, int col , int size)
    {
        return (row >= 0) && (row < size) &&
                (col >= 0) && (col < size);
    }

    public static boolean validPoint(int[][] maze , Point point){
        return maze[point.x][point.y] == 1;
    }

    public static void printMaze(int[][] maze , int n){
        for(int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }
}
