package com.ming.dataStructure.Queue;

import java.util.Iterator;

public class ArrayQueue3 <E> implements Queue<E>,Iterable<E>{


  private int head = 0;
  private int tail = 0;
  private final E[] array;
  private final int capacity;

  public ArrayQueue3(int capacity){
    if(((capacity & capacity - 1) != 0)){
      throw new IllegalArgumentException("capacity必须为2的幂");
    }
    this.capacity = capacity;
    array = (E[]) new Object[this.capacity];
  }
  @Override
  public boolean offer(E value) {
    if (isFull()) {
      return false;
    }
    array[tail & capacity - 1] = value;
    tail++;
    return true;
  }

  @Override
  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E value = array[head & capacity - 1];
    head++;
    return value;
  }

  @Override
  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return array[head & capacity - 1];
  }

  @Override
  public boolean isEmpty() {
    return tail - head == 0;
  }

  @Override
  public boolean isFull() {
    return tail - head == capacity;
  }

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
        E value = array[p & capacity - 1];
        p++;
        return value;
      }
    };
  }
}
