package com.ming.dataStructure.DisJointSets;

/**
 * @author Shukun.Li
 */
public class UnionFindFastFindSlowUnion {

    private int[] parent;
    private int size;

    public UnionFindFastFindSlowUnion(int size){
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int element){
        // loop until find the root elements
        while(element != parent[element]){
            element = parent[element];
        }
        return element;
    }

    public boolean isConnected(int firstElement, int secondElement){
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement,int secondElement){
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);
        if(firstRoot == secondRoot){
            return ;
        }
        parent[firstRoot] = secondRoot;
    }

    private void printArr(){
        for (int parent: this.parent){
            System.out.println(parent + "\t");
        }
    }

    public static void main(String[] args) {
        int n = 10;
        UnionFindFastFindSlowUnion union = new UnionFindFastFindSlowUnion(n);
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
