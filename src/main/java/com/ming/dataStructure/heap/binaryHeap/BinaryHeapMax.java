package com.ming.dataStructure.heap.binaryHeap;

import java.util.Arrays;
import java.util.Random;

/**
 * 二叉堆,父节点总大于子节点（最大堆),用数组实现
 */
public class BinaryHeapMax {
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 当前最后一个数据下标
     */
    private int currentSize;
    private AnyType[] array;

    public BinaryHeapMax(){}

    public BinaryHeapMax(int capacity){
        currentSize = 0;
        array = new  AnyType[capacity];
    }

    public BinaryHeapMax(AnyType[] item){
        currentSize = item.length;
        array = new AnyType[(currentSize + 2) * 11 / 10];
        int i = 1 ;
        for(AnyType anyType : item){
            array[i++] = anyType;
        }

    }


    public AnyType[] getAppHeapData(){
        return Arrays.copyOfRange(array,1,currentSize + 1);
    }

    /**
     * 获取当前最大堆数据量
     * @return
     */
    public Integer heapSize(){
        return currentSize;
    }
    
    public void buildHeap(){
        for (int i = currentSize /2; i > 0 ; i--) {
            percoateDown(i);
        }
    }

    public void percoateDown(int hole){
        int child = 0;
        AnyType tem = array[hole];
        for(;hole * 2 <= currentSize; hole = child){
            child = 2 * hole;

            if(child <= currentSize && array[child].compareTo(array[child + 1] )< 0 && array[child + 1] != null){
                child ++;
            }
            if(tem.compareTo(array[child]) < 0){
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tem;
    }
    
    public void enlargeArray(int newSize){
        AnyType[] newArray = new AnyType[newSize];
        for (int i = 0; i < array.length ; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void insert(AnyType x){
        if(currentSize == array.length){
            enlargeArray(currentSize * 2 + 1);
        }
        int hole = ++currentSize;
        for(array[0] = x; x.compareTo(array[hole/2]) > 0; hole = hole / 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    /**
     * 查找最大值,二叉堆最大值是根节点
     * @return
     */
    public AnyType findMax(){
        return array[1];
    }

    public AnyType deleteMax(){
        if(isEmpty()){
            return null;
        }
        AnyType max  = findMax();
        array[1] = array[currentSize --];
        array[currentSize + 1] = null;
        percoateDown(1);
        return max;
    }

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

    public static void main(String[] args) {
        Integer size = 10;
        BinaryHeapMax binaryHeapMax = new BinaryHeapMax((size + 2) * 11 / 10);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            AnyType anyType = new AnyType(random.nextInt(100));
            binaryHeapMax.insert(anyType);
        }
        while(!binaryHeapMax.isEmpty()){
            System.out.println(binaryHeapMax.deleteMax().getElement());
        }

    }
    
}
