package com.ming.dataStructure.blockingQueue;

public interface blockingQueue<E> { //阻塞队列

  void offer(E e) throws InterruptedException;

  boolean offer(E e,long timeout) throws  InterruptedException;

  E poll() throws InterruptedException;


}
