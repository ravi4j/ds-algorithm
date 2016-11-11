package com.rs.ds.tree;

import java.util.*;
import static com.rs.ds.tree.SwapNodes.Node;

/**
 * Created by ravisharma on 10/29/2016.
 */
public class SwapNodes {

    static class Node {
        int data;
        Node left;
        Node right;

        @Override
        public String toString() {
            return "" + this.data;
        }
    }
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("tree/SwapNodes-Input.txt"));
        int N = sc.nextInt();
        int i = 0;
        int[] data = new int[2 * N + 1];
        data[i++] = 1;
        int t = 0;
        while (t < N) {
            data[i++] = sc.nextInt();
            data[i++] = sc.nextInt();
            t++;
        }
        Node root = buildTree(data);
        inOrder(root);
        System.out.println();
        int T = sc.nextInt();
        while( T > 0 ){
            int K =  sc.nextInt();
            int j = 1;
            while(j <= height(root)) {
                 processLevel(root, j*K);
                j++;
            }
            inOrder(root);
            System.out.println();
            T--;
        }
    }

    public static Node buildTree(int[] array) {
        Deque<Node> deque = new ArrayDeque<>();
        int i = 0;
        Node root = new Node();
        root.data = array[0];
        deque.push(root);
        Node current;
        while (!deque.isEmpty()) {
            current = deque.poll();
            // left Node
            if (2 * i + 1 > array.length) break;
            if (array[2 * i + 1] != -1) {
                current.left = new Node();
                current.left.data = array[2 * i + 1];
                deque.add(current.left);
            }
            if (2 * i + 2 > array.length) break;
            if (array[2 * i + 2] != -1) {
                current.right = new Node();
                current.right.data = array[2 * i + 2];
                deque.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void inOrder(Node root){
        if( root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static int height(Node root){
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if(left > right){
            return left + 1;
        } else {
            return right + 1;
        }
    }

  /*  public static void levelOrder(Node root){
        for(int i = 0 ;i <= height(root);i++){
           processLevel(root,i);
           System.out.println();
        }
    } */

    private static void processLevel(Node root, int level){
        if(root == null) return ;
        if( level == 1 ){
            if( root.left != null || root.right != null){
                Node temp = root.left;
                root.left = root.right;
                root.right = temp;
            }
        }else {
             processLevel(root.left, level - 1);
             processLevel(root.right, level - 1);
        }
    }


}
