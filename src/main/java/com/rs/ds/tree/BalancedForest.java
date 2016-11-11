package com.rs.ds.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by ravisharma on 11/8/2016.
 */
public class BalancedForest {

    static class Tree {
        int V;
        Node[] nodes;
        Edge[] edges;
        int noOfEdges;
        class Node {
            int name;
            int coins;
            Node[] adj;
            int noOfAdj;

            @Override
            public String toString(){
                return  "( " + this.name + " , " + this.coins + " )";
            }
        }

        class Edge {
            Node to;
            Node from;
        }

        public Tree(int n, int[] coins){
            this.V = n + 1;
            this.nodes = new Node[V];
            this.edges = new Edge[V-1];
            for(int i = 0 ; i < V ; i++){
                Node node = new Node();
                node.name = i + 1;
                if(i < coins.length)
                     node.coins = coins[i];
                node.adj = new Node[V-1];
                nodes[i] = node;
            }
        }

        public void bfs(){
            if(this.nodes == null || this.nodes.length == 0)
                return;
            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(nodes[0]);
            while(!queue.isEmpty()){
                Node node = queue.poll();
                if(node != null) {
                    System.out.println(node);
                    for(int i = 0 ; i < node.noOfAdj ; i++)
                      queue.offer(node.adj[i]);
                }
            }
        }

        public void dfs(){
            if(this.nodes == null || this.nodes.length == 0)
                return;
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(nodes[0]);
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node != null) {
                    System.out.println(node);
                    for(int i = 0 ; i < node.noOfAdj ; i++)
                        stack.push(node.adj[i]);
                }
            }
        }

        public void buildEdge(int u , int v){
            Edge edge = new Edge();
            Node x = nodes[u-1];
            Node y = nodes[v-1];
            x.adj[x.noOfAdj++] = y;
           // y.adj[y.noOfAdj++] = x;
            edge.from = x;
            edge.to =  y;
            edges[noOfEdges++] = edge;
        }

        public void printTree(){
            for(Node node : nodes){
                System.out.println( node +  " - > " + Arrays.toString(node.adj));
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("tree/BalancedForest-Input.txt"));
        int t = sc.nextInt();
        while(t > 0){
            int n = sc.nextInt();
            int[] coins = new int[n];
            for(int i = 0 ; i < n ; i++)
                coins[i] = sc.nextInt();
            Tree tree = new Tree(n , coins);
            for(int i = 0 ; i < n - 1 ; i++)
                tree.buildEdge(sc.nextInt() , sc.nextInt());
            tree.printTree();
            tree.bfs();
            tree.dfs();
            t--;
        }

    }
}
