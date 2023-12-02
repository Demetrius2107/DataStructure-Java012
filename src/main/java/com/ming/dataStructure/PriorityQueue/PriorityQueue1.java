package com.ming.dataStructure.PriorityQueue;

import com.ming.dataStructure.Queue.Queue;

public class PriorityQueue1 <E extends Priority> implements Queue {

  Priority[] array;
  int size;

  public PriorityQueue1(int capacity){
    array = new Priority[capacity];
  }


  private int selectMax(){
    int max = 0;
    for(int i = 1;i < size; i++ ){
      if(array[i].priority() > array[max].priority()){
        max = i;
      }
    }
    return max;
  }

  /**
   * 向队列尾插入值
   *
   * @param e 待插入值
   * @return 插入成功返回 true, 插入失败返回 false
   */
  public boolean offer(E e) {
    if(isFull()){
      return false;
    }
    array[size++] = e;
    return true;
  }

  /**
   * 向队列尾插入值
   *
   * @param value 待插入值
   * @return 插入成功返回 true, 插入失败返回 false
   */
  @Override
  public boolean offer(Object value) {
    return false;
  }

  /**
   * 从队列头获取值, 并移除
   *
   * @return 如果队列非空返回对头值, 否则返回 null
   */
  @Override
  public Object poll() {
    if(isEmpty()){
      return null;
    }
    int max = selectMax();
    E e = (E) array[max];
    remove(max);
    return e;
  }

  private void remove(int index){
    if(index < size - 1){
      //移动
      System.arraycopy(array,index + 1, array,index,size - 1 - index);
    }
    array[--size] = null;//help GC

  }

  /**
   * 从队列头获取值, 不移除
   *
   * @return 如果队列非空返回对头值, 否则返回 null
   */
  @Override
  public E peek() {
    if(isEmpty()){
      return null;
    };
    int max = selectMax();
    return (E) array[max];
  }

  /**
   * 检查队列是否为空
   *
   * @return 空返回 true, 否则返回 false
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 检查队列是否已满
   *
   * @return 满返回 true, 否则返回 false
   */
  @Override
  public boolean isFull() {
    return size == array.length;
  }
}
