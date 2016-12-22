package com.rs.ds.tree;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by ravisharma on 11/14/2016.
 */
public class BForestSolution {

    static class Tree {

        int V;
        Node[] nodes;
        int noOfEdge;
        Pair<Node , Node>[] edges;

        public Tree(int v , int[] coins){
            this.V = v;
            this.nodes = new Node[V];
            this.edges = new Pair[V-1];
            this.noOfEdge = 0;
            for(int i = 0 ; i < V ; i++) {
                Node node = new Node(i+1 , coins[i]);
                nodes[i] = node;
            }
        }

        Node getRootNode(){
            return Arrays.asList(nodes).stream().filter( n -> n.inDegree == 0 && n.outDegree == 3).findFirst().orElse(null);
        }

        void  addEdge(int f , int t){
            Node from = nodes[f-1];
            Node to = nodes[t-1];
            from.children.add(to);
            from.outDegree++;
            to.inDegree++;
            to.children.add(from);
            Pair<Node,Node> edge = new Pair<>(nodes[f-1], nodes[t -1]);
            this.edges[noOfEdge++] = edge;
        }

        int findBalancedForest(){
            Node root = getRootNode();
            if(root == null) return -1;
            List<Integer> list = new ArrayList<>();
            dfsSum(root , 0 , list);
            System.out.print(list);
           if(list.size() > 1 && list.size() < 4){
               if(list.size() == 2){
                   if(list.get(0) == list.get(1)){
                       return  list.get(0);
                   }else {
                       return -1;
                   }
               }
               if(list.size() == 3){
                  Set<Integer> set = new HashSet<>(list);
                  if(set.size() == 2){
                      Iterator<Integer> itr = set.iterator();
                      return Math.abs(itr.next() - itr.next());
                  } else{
                      return -1;
                  }
               }
           } else {
               return -1;
           }
            return 0;
        }

        int dfsSum(Node root , int sum , List<Integer> list){
            if(root == null) return 0;
            Node current = root;
            if(current.children.size() !=0) {
                sum = current.coins + sum;
            }else {
                System.out.println("id=" + current.index+ " ->" + current.coins + " ," + sum +" = " +  (current.coins + sum));
                list.add(current.coins + sum);
                sum = 0;
            }
            for(Node child : current.children) {
                sum = dfsSum(child , sum , list);
            }
             return sum;
        }

        void printTree(){
            Arrays.asList(nodes).forEach(node-> System.out.println(node +" -> " + node.children));
        }

        class Node {
            int index;
            int coins;
            int inDegree;
            int outDegree;
            boolean visited;
            List<Node> children;

            public Node(int index , int coins){
                this.index = index;
                this.coins = coins;
                this.children  = new ArrayList<>(V);
            }

            @Override
            public String toString(){
                return "( id=" + index + " in=" + inDegree + " , coins=" + coins + ")";
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("tree/BalancedForest-Input.txt"));
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            int[] coins = new int[n];
            for (int i = 0; i < n; i++)
                coins[i] = sc.nextInt();
             Tree tree = new Tree(n , coins);
            for(int i = 0 ; i < n - 1 ; i++)
                tree.addEdge(sc.nextInt() , sc.nextInt());
            tree.printTree();
            System.out.println(tree.findBalancedForest());
            //System.out.println(list);
            t--;
        }
    }
}
