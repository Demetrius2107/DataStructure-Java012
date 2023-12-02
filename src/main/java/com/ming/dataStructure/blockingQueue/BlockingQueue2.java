package com.ming.dataStructure.blockingQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2 <E> implements blockingQueue<E>{

  private final E[] array;
  private int head = 0;
  private int tail = 0;
  private final AtomicInteger size = new AtomicInteger(0);

  ReentrantLock headLock = new ReentrantLock();
  Condition headWaits = headLock.newCondition();
  ReentrantLock tailLock = new ReentrantLock();
  Condition tailWaits = tailLock.newCondition();

  public BlockingQueue2(int capacity){
    this.array = (E[]) new Object[capacity];
  }



  @Override
  public void offer(E e) throws InterruptedException {
      int c;
      tailLock.lockInterruptibly();
      try {
        while (isFull()) {
          tailWaits.await();
        }
        array[tail] = e;
        if (++tail == array.length) {
          tail = 0;
        }
        c = size.getAndIncrement();
        //a.队列不满，但不是从满 -> 不满，由此offer线程唤醒其他offer线程
        if (c + 1 < array.length) {
          tailWaits.signal();
        }
      }finally{
          tailLock.unlock();
        } if (c == 0) {
          headLock.lock();
          try {
            headWaits.signal();
          } finally {
            headLock.unlock();
          }
        }
  }

  private boolean isEmpty(){
    return size.get() == 0;
  }
  private boolean isFull() {
    return size.get() == array.length;
  }

  @Override
  public boolean offer(E e, long timeout) throws InterruptedException {
    return false;
  }

  @Override
  public E poll() throws InterruptedException {
    E e;
    int c;

    headLock.lockInterruptibly();
    try{
      while(isEmpty()){
        headWaits.await();
      }
      e = array[head];
      if(++head == array.length){
        head = 0;
      }
      c = size.getAndDecrement();
      //b.队列不空，但是不从0变化到不空，由此poll线程通知其他poll线程
      if(c > 1){
        headWaits.signal();
      }
    }finally {
      headLock.unlock();
    }
    //a . 从满->不满，由此poll线程唤醒等待的offer线程
    if(c== array.length){
      tailLock.lock();
      try{
        tailWaits.signal();
      } finally {
        tailLock.unlock();
      }
    }
    return e;
  }
}
