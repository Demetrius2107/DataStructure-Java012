package com.ming.dataStructure.heap.binaryHeap;

import java.util.Random;

/**
 * 二叉堆,父节点总小于子节点（最小堆）,基于数组实现
 * @author Shukun.Li
 */
public class BinaryHeap {


    private static final int DEFAULT_CAPACITY = 10;

    // 当前最后一个数据下标
    private int currentSize;

    private AnyType[] array;

    public BinaryHeap(){}

    public BinaryHeap(int capacity){
        currentSize = 0;
        array = new AnyType[capacity];
    }

    public BinaryHeap(AnyType[] item){
        currentSize = item.length;
        array = new AnyType[(currentSize + 2) * 11 / 10];
        int i = 1;
        for(AnyType anyType : item){
            array[i++] = anyType;
        }
        buildHeap();
    }

    public void buildHeap(){
        for(int i = currentSize / 2; i > 0; i--){
            percoateDown(i);
        }
    }

    /**
     * 将顶部较大的节点逐步下滤
     * @param hole
     */
    public void percoateDown(int hole){
        int child = 0;
        AnyType tem = array[hole];
        for(;hole *2 <= currentSize; hole = child){
            child = 2 * hole;
            if(child <= currentSize && array[child].compareTo(array[child+1]) > 0){
                child ++;
            }
            if(array[child].compareTo(tem) < 0){
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tem;
    }

    /**
     * 二叉堆中添加节点（上滤方法）
     * @param x
     */
    public void insert(AnyType x){
        if(currentSize == array.length){
            enlargeArray(currentSize * 2 + 1);
        }
        int hole = ++currentSize;
        for(array[0] = x; x.compareTo(array[hole/2]) < 0; hole = hole /2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    /**
     * 扩容
     * @param newSize
     */
    public void enlargeArray(int newSize){
        AnyType[] newArray = new AnyType[newSize];
        for(int i = 0; i < array.length; i ++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * 查找最小值,二叉堆最小值是根节点
     * @return
     */
    public AnyType findMin(){
        return array[1];
    }

    public AnyType deleteMin(){
        if(isEmpty()){
            return null;
        }
        AnyType min = findMin();
        array[1] = array[currentSize --];
        array[currentSize + 1] = null;
        percoateDown(1);
        return min;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        if(array == null || array.length <= 0 || currentSize == 0){
            return true;
        }
        for(AnyType anyType : array){
            if(anyType != null){
                return false;
            }
        }
        return true;
    }

    /**
     * 置空整个二叉堆
     */
    public void makeEmpty(){
        array = new AnyType[DEFAULT_CAPACITY];
    }

    public static void main(String[] args) {
        Integer size = 10;
        BinaryHeap binaryHeap = new BinaryHeap((size + 2) * 11 / 10);
        Random random = new Random();
        for (int i = 0; i < size ; i++) {
            AnyType anyType = new AnyType(random.nextInt(100));
            binaryHeap.insert(anyType);
        }
        while(!binaryHeap.isEmpty()){
            System.out.println(binaryHeap.deleteMin().getElement());
        }

    }

}
