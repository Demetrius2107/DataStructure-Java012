package com.ming.dataStructure.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList  implements Iterable<Integer>{

  private Node head;

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<Integer> iterator() {
    return new NodeIterator();
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  public class NodeIterator implements  Iterator<Integer>  {
    Node curr = head;

    public boolean hasNext(){
      return curr != null;
    }

    public Integer next(){
      int value = curr.value;
      curr = curr.next;
      return  value;
    }

  }

  private static class Node{
    //节点类
    int value;
    Node next;

    public Node(int value,Node next){
      this.value = value;
      this.next = next;
    }
  }

  //头部添加
  public void addFirst(int value){
    this.head = new Node(value,this.head);
  }

  //while遍历
  public void loop(){
    Node curr = this.head;
    while(curr != null){
      //do something
      curr = curr.next;
    }
  }


  //递归遍历
  private void recursion(Node curr){
    if(curr == null){
      return;
    }
    //do something Front
    recursion(curr.next);
    //do something end
  }

  //尾部添加
  private Node findLast(){
    if(this.head == null){
      return null;
    }
    Node curr;
    for(curr = this.head;curr.next!=null;){
      curr =curr.next;
    }
    return curr;
  }

  public void addLast(int value){
    Node last = findLast();
    if(last == null){
      addFirst(value);
      return;
    }
    last.next = new Node(value,null);
  }

  //根据索引获取
  private  Node findNode(int index){
    int i = 0;
    for(Node curr = this.head;curr != null; curr = curr.next,i++){
      if(index == i){
        return curr;
      }
    }
    return null;
  }

  private IllegalAccessException illegalIndex(int index){
    return new IllegalAccessException(String.format("index [%d] 不合法%n",index));
  }

  private int get(int index) throws IllegalAccessException {
    Node node = findNode(index);
    if(node != null){
      return node.value;
    }
    throw  illegalIndex(index);
  }

  //插入
  public void insert(int index,int value) throws IllegalAccessException {
    if(index == 0){
      addFirst(value);
      return;
    }
    Node prev = findNode(index - 1);//找到上一个节点
    if(prev == null){
      //找不到
      throw illegalIndex(index);
    }
    //插入和删除，都必须找到上一个节点
    prev.next = new Node(value,prev.next);
  }

  public void remove(int index) throws IllegalAccessException {
    if(index == 0){
      if(this.head != null){
        this.head = this.head.next;
        return;
      } else{
        throw illegalIndex(index);
      }
    }
    Node prev= findNode(index - 1);
    Node curr;
    if(prev!= null&&(curr  = prev.next)!=null){
      prev.next = curr.next;
    } else {
      throw illegalIndex(index);
    }
  }


}


