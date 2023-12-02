package com.ming.dataStructure.Queue;

import java.util.Iterator;

public class ArrayQueue2 <E> implements Queue<E>,Iterable<E>{


  //判断空、满方法 version2
  private int head = 0;
  private int tail = 0;
  private final E[] array;
  private final int capacity;
  private int size = 0;

  @SuppressWarnings("all")
  public ArrayQueue2(int capacity){
    this.capacity = capacity;
    array = (E[]) new Object[capacity];
  }

  /**
   * 向队列尾插入值
   *
   * @param value 待插入值
   * @return 插入成功返回 true, 插入失败返回 false
   */
  @Override
  public boolean offer(E value) {
    if(isFull()){
      return false;
    }
    array[tail] = value;
    tail = (tail + 1) % capacity;
    size ++;
    return true;
  }

  /**
   * 从队列头获取值, 并移除
   *
   * @return 如果队列非空返回对头值, 否则返回 null
   */
  @Override
  public E poll() {
    if(isEmpty()){
      return null;
    }
    E value = array[head];
    head = (head + 1) % capacity;
    size --;
    return value;
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
    }
    return array[head];
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
    return size == capacity;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      int p = head;

      @Override
      public boolean hasNext() {
        return p != tail;
      }

      @Override
      public E next() {
        E value = array[p];
        p = (p + 1) % capacity;
        return value;
      }
    };
  }
}
