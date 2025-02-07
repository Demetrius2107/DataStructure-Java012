package com.ming.dataStructure.DisJointSets;

/**
 * @author Shukun.Li
 */
public class UnionFindFastFindFastUnion {

    private int[] parent;

    private int[] weight;

    private int size;

    public UnionFindFastFindFastUnion(int size) {
        this.parent = new int[size];
        this.weight = new int[size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.weight[i] = 1;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        if (firstRoot == secondRoot) {
            return;
        }

        if (weight[firstRoot] > weight[secondRoot]) {
            parent[secondRoot] = firstRoot;
            weight[firstRoot] += weight[secondRoot];
        } else if (weight[firstRoot] <= weight[secondRoot]) {
            parent[firstRoot] = secondRoot;
            weight[secondRoot] += weight[firstRoot];
        }
    }

    private void printArr(int[] arr){
        for(int p : arr){
            System.out.println(p + "\t");
        }
        System.out.println();
    }



}
