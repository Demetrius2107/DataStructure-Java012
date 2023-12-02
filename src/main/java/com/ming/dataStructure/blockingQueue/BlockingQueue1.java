package com.ming.dataStructure.blockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @param <E>
 */
@SuppressWarnings("all")
public class BlockingQueue1<E> implements blockingQueue<E> {

  private final E[] array;
  private int head;
  private int tail;
  private int size;

  public BlockingQueue1(int capacity){
    array = (E[]) new Object[capacity];
  }

  private ReentrantLock lock = new ReentrantLock();
  private Condition headWaits = lock.newCondition();
  private Condition tailWaits = lock.newCondition();

  private boolean isEmpty(){
    return size == 0;
  }

  private boolean isFull(){
    return size == array.length;
  }

  @Override
  public void offer(E e) throws InterruptedException {
    lock.lockInterruptibly();
    try{
      while(isFull()){
          tailWaits.await();
      }
      array[tail] = e;
      if(++tail == array.length){
        tail = 0;
      }
      size ++;
      headWaits.signal();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean offer(E e, long timeout) throws InterruptedException {
    lock.lockInterruptibly();
    try{
      long t = TimeUnit.MILLISECONDS.toNanos(timeout);
      while(isFull()){
        if(t <= 0){
          return false;
        }
        t = tailWaits.awaitNanos(t);//最多等待多少时间
      }
      array[tail] = e;
      if(++tail == array.length){
        tail = 0;
      }
      size++;
      headWaits.signal();
      return true;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public E poll() throws InterruptedException {
    lock.lockInterruptibly();
    try{
      while(isEmpty()){
        headWaits.await();
      }
      E e = array[head];
      array[head] = null;
      if(++head == array.length){
        head = 0;
      }
      size--;
      tailWaits.signal();
      return e;
    } finally {
      lock.unlock();
    }
  }




}
