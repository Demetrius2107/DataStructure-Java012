package com.ming.dataStructure.deque;

import java.util.Iterator;

public class LinkedListDeque<E> implements Deque<E>,Iterable<E>{

  static class Node<E> {
    Node<E> prev;
    E value;
    Node<E> next;

    public Node(Node<E> prev,E value,Node<E> next){
      this.prev = prev;
      this.value = value;
      this.next = next;
    }
  }

  Node<E> sentinel = new Node<>(null,null,null);
  int capacity;
  int size;

  public LinkedListDeque(int capacity){
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    this.capacity = capacity;
  }
  @Override
  public boolean offerFirst(E e) {
    if(isFull()){
      return false;
    }
    size ++ ;
    Node<E> a = sentinel;
    Node<E> b = sentinel.next;
    Node<E> offered = new Node<>(a,e,b);
    a.next = offered;
    b.prev = offered;
    return true;
  }

  @Override
  public boolean offerLast(E e) {
    if(isFull()){
      return false;
    }
    size ++;
    Node<E> a = sentinel.prev;
    Node<E> b = sentinel;
    Node<E> offered = new Node<>(a,e,b);
    a.next = offered;
    b.prev = offered;
    return true;
  }

  @Override
  public E pollFirst() {
    if(isEmpty()){
      return null;
    }
    Node<E> a = sentinel;
    Node<E> polled = sentinel.next;
    Node<E> b = polled.next;
    a.next = b;
    b.prev = a;
    size --;
    return polled.value;
  }

  @Override
  public E pollLast() {
    if(isEmpty()){
      return null;
    }
    Node<E> polled = sentinel.prev;
    Node<E> a = polled.prev;
    Node<E> b = sentinel;
    a.next = b;
    b.prev = a;
    size --;
    return polled.value;
  }

  @Override
  public E peekFirst() {
    if(isEmpty()){
      return null;
    }
    return sentinel.next.value;
  }

  @Override
  public E peekLast() {
    if(isEmpty()){
      return null;
    }
    return sentinel.prev.value;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

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
      Node<E> p = sentinel.next;

      @Override
      public boolean hasNext() {
        return p != sentinel;
      }

      @Override
      public E next() {
        E value = p.value;
        p = p.next;
        return value;
      }
    };
  }
}
