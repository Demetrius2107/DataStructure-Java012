package com.ming.dataStructure.DisJointSets;

import java.awt.*;

/**
 * @author Shukun.Li
 */
public class UnionFind {

    // Array as symbol of UnionFind elements;
    private int[] id;

    // UnionFind elements number;
    private int size;

    // init a new UnionFind
    public UnionFind(int size){
        this.size = size;
        id = new int[size];
        // when start every element is one unionFind
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    // find element in where sets
    public int find(int element){
        return id[element];
    }

    // judge two element is in same sets
    public boolean isConnected(int firstElement,int secondElement){
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement,int secondElement){
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        // if the two elements not in same sets, then union
        if(firstElement != secondElement){
            for (int i = 0; i < this.size; i++) {
                if(id[i] == firstUnion){
                    id[i] = secondUnion;
                }
            }
        }
    }

    // print array way
    private void printArr(){
        for (int id : this.id){
            System.out.println(id + "\t");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int n = 10;
        UnionFind union = new UnionFind(n);
        System.out.println("初始：");
        union.printArr();

        System.out.println("连接了5 6");
        union.unionElements(5, 6);
        union.printArr();

        System.out.println("连接了1 2");
        union.unionElements(1, 2);
        union.printArr();

        System.out.println("连接了2 3");
        union.unionElements(2, 3);
        union.printArr();

        System.out.println("连接了1 4");
        union.unionElements(1, 4);
        union.printArr();

        System.out.println("连接了1 5");
        union.unionElements(1, 5);
        union.printArr();

        System.out.println("1  6 是否连接：" + union.isConnected(1, 6));

        System.out.println("1  8 是否连接：" + union.isConnected(1, 8));

    }

}
