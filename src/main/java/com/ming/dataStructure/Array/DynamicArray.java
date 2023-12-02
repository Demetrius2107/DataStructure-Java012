package com.ming.dataStructure.Array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray  implements Iterable<Integer>{

  private int size = 0;//逻辑大小
  private int capacity = 8;//容量
  private int[] array = {};


  public void addLast(int element){
    add(size,element);
  }


  /**
   * 向[0..size] 位置添加元素
   *
   * @param index 索引位置
   * @param element 待添加元素
   */
  public void add(int index,int element){
    checkAndGrow();
    //添加逻辑
    if(index >= 0 && index < size){
      System.arraycopy(array,index,array,index+1,size-index);
    }
    array[index] = element;
    size++;
  }


  public void checkAndGrow(){
    //容量检查
    if(size == 0){
      array = new int[capacity];
    } else if (size == capacity){
      //扩容
      capacity += capacity >> 1;
      int[] newArray = new int[capacity];
      System.arraycopy(array,0,newArray,0,size);
      array = newArray ;
    }
  }


  public int remove(int index){
    int removed = array[index];
    if(index < size -1){
      //向前挪动
      System.arraycopy(array,index+1,array,index,size-index-1);
    }
    size --;
    return removed;
  }

  /**
   * 查询元素
   * @param index 索引位置，在[0..size)区间内
   * @return 该索引位置的元素
   */
  public int get(int index){
    return array[index];
  }

  /**
   * 遍历方法1
   * @param consumer 遍历要执行的操作，入参；每个元素
   */
  public void foreach(Consumer<Integer> consumer){
    for (int i = 0; i < size; i++) {
      consumer.accept(array[i]);
    }
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   * 迭代器遍历
   * @return an Iterator.
   */
  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      int i = 0;

      @Override
      public boolean hasNext() {
        return i < size;
      }

      @Override
      public Integer next() {
        return array[i++];
      }
    };
  }

  /**
   * 遍历方法
   * @return  stream流
   */
  public IntStream stream(){
    return IntStream.of(Arrays.copyOfRange(array,0,size));
  }

}
