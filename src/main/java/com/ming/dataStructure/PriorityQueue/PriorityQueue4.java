package com.ming.dataStructure.PriorityQueue;

import com.ming.dataStructure.Queue.Queue;

//堆实现
public class PriorityQueue4<E extends Priority> implements Queue<E> {

  Priority[] array;
  int size;

  public PriorityQueue4(int capacity){
    array = new Priority[capacity];
  }

  /**
   * 向队列尾插入值
   *
   * @param offerd 待插入值
   * @return 插入成功返回 true, 插入失败返回 false
   */
  @Override
  public boolean offer(E offerd) {
    if(isFull()){
      return false;
    }
    int child = size++;
    int parent = (child - 1) / 2;
    while(child > 0 && offerd.priority()>array[parent].priority()){
      array[child] = array[parent];
      child = parent;
      parent = (child - 1) / 2;
    }
    array[child] = offerd;
    return true;
  }

  /**
   * 从队列头获取值, 并移除
   *
   * @return 如果队列非空返回对头值, 否则返回 null
   */
  @Override
  public E poll() {
    return null;
  }

  /**
   * 从队列头获取值, 不移除
   *
   * @return 如果队列非空返回对头值, 否则返回 null
   */
  @Override
  public E peek() {
    return null;
  }

  /**
   * 检查队列是否为空
   *
   * @return 空返回 true, 否则返回 false
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * 检查队列是否已满
   *
   * @return 满返回 true, 否则返回 false
   */
  @Override
  public boolean isFull() {
    return false;
  }
}
