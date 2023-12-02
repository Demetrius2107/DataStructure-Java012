package com.ming.dataStructure.heap;

import java.util.Arrays;

public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity){
      this.array = new int[capacity];
      this.size = array.length;
      heapify();
    }

    //建堆
  private void heapify(){
      //如何找到这个非叶子节点  size / 2 - 1
    for(int i = size / 2 - 1;i>= 0;i--){
      down(i);
    }
  }

  private void down(int parent){
      int left = parent * 2 + 1;
      int right = left + 1;
      int max = parent;
      if(left < size && array[left] > array[max]){
        max = left;
      }
      if(right < size && array[right] > array[max]){
        max = right;
      }
      if(max != parent) {
        //找到了更大的孩子
        swap(max, parent);
        down(max);
      }
  }

  private void swap(int i ,int j){
      int t =array[i];
      array[i] = array[j];
      array[j] = t;
  }


  public static void main(String[] args) {
    int[] array = {2,3,1,7,6,4,5};
   // MaxHeap heap = new MaxHeap(array);
   // System.out.println(Arrays.toString(heap.array));
  }

}
