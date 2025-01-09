package com.ming.dataStructure.Tree;

import java.util.LinkedList;

/**
 * @author Shukun.Li
 */
public class BinaryTree {

    private int size;

    private Node root;

    public BinaryTree() {
    }

    public BinaryTree(Integer[] elements) {
        this();
        root = addAllRecur(elements);
    }

    private static class Node {

        Integer data;

        Node right;

        Node left;

        Node(Integer data) {
            this.data = data;
        }
    }

    // 前序遍历递归创建二叉树
    private Node addAllRecur(Integer[] elements) {
        Integer variable = elements[size++];
        if (variable == null) {
            return null;
        }

        Node node = new Node(variable);
        node.left = addAllRecur(elements);
        node.right = addAllRecur(elements);
        return node;
    }

    // 前序遍历,递归
    public void preOrderRecur() {
        preOrderRecur(root);
    }

    public void preOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data + " -> ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    public void preOrder() {
        if (root == null) {
            return;
        }
        Node current;
        LinkedList<Node> s = new LinkedList<Node>();
        s.addFirst(root);
        while (!s.isEmpty()) {
            current = s.removeFirst();
            System.out.println(current.data + " -> ");
            if (current.right != null) {
                s.addFirst(current.right);
            }
            if (current.left != null) {
                s.addFirst(current.left);
            }
        }
    }

    // 中序遍历,递归
    public void inOrderRecur() {
        inOrderRecur(root);
    }

    public void inOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        inOrderRecur(root.left);
        System.out.println(root.data + "->");
        inOrderRecur(root.right);
    }

    // 中序遍历,非递归
    public void inOrder() {
        Node current = root;
        LinkedList<Node> s = new LinkedList<Node>();
        while (current != null || !s.isEmpty()) {
            while (current != null) {
                s.addFirst(current);
                current = current.left;
            }
            if (!s.isEmpty()) {
                current = s.removeFirst();
                System.out.println(current.data + "->");
                current = current.right;
            }
        }
    }

    // 后序遍历,递归
    public void postOrderRecur() {
        postOrderRecur(root);
    }

    public void postOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        postOrderRecur(root.left);
        postOrderRecur(root.right);
        System.out.println(root.data + " -> ");
    }

    // 后序遍历,非递归
    public void postOrder() {
        Node current = root;
        LinkedList<Node> s1 = new LinkedList<Node>();
        LinkedList<Node> s2 = new LinkedList<Node>();
        while (current != null || !s1.isEmpty()) {
            while (current != null) {
                s1.addFirst(current);
                s2.addFirst(current);
                current = current.right;
            }
            if (!s1.isEmpty()) {
                current = s1.removeFirst();
                current = current.left;
            }
        }
        while (!s2.isEmpty()) {
            System.out.println(s2.removeFirst().data + " -> ");
        }
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }
        LinkedList<Node> q = new LinkedList<Node>();
        q.addFirst(root);
        Node current;
        while (!q.isEmpty()) {
            current = q.removeLast();
            System.out.println(current.data + " ->");
            if (current.left != null) {
                q.addFirst(current.left);
            }
            if (current.right != null) {
                q.addFirst(current.right);
            }
        }
    }


    public Integer[] toArray() {

        LinkedList<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return null;
        }
        LinkedList<Node> q = new LinkedList<Node>();
        q.addFirst(root);
        Node current;
        while (!q.isEmpty()) {
            current = q.removeLast();
            list.add(current.data);
            if (current.left != null) {
                q.addFirst(current.left);
            }
            if (current.right != null) {
                q.addFirst(current.right);
            }
        }
        // 将list转化为数组
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);
        return arr;
    }


}
