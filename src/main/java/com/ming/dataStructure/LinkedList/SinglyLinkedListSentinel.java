package com.ming.dataStructure.LinkedList;


//用一个不参与数据存储的特殊node作为哨兵,它一般被称为哨兵或者哑元，拥有哨兵节点的链表被称为带头节点
//

import java.util.Iterator;
import java.util.function.Consumer;

/**
*单向链表（带哨兵)
**/
public class SinglyLinkedListSentinel  implements Iterable<Integer>{

  //private final Node head;
  //private final Node tail;

  private Node head =new Node(Integer.MIN_VALUE,null);

  @Override
  public Iterator<Integer> iterator() {
    //匿名内部类 -> 带名字内部类
    return new NodeIterator();
  }

  public class NodeIterator implements Iterator<Integer>{
    Node p = head.next;

    @Override
    public boolean hasNext() { // 是否有下一个元素
      return p != null;
    }

    @Override
    public Integer next() { //返回当前值 ，并指向下一个元素
      int v = p.value;
      p = p .next;
      return v;
    }
  }

  /**
   * 节点类
   **/
  private static class Node{
    //节点类
    int value;//值
    //SinglyLinkedList.Node next;
    Node next;//下一个节点指针
    public Node(int value, Node next){
      this.value = value;
      this.next = next;
    }
  }

  //根据索引获取节点
 /* private Node findNode(int index){
    int i = -1;
    for(Node p = head;p != null;p = p.next,i++){
      if(i == index){
       // return curr;
        return p;
      }
    }
    return null;//未找到
  }

  public void addFirst(int value){
    insert(0,value);
  }
  */

  /**
   * 向链表头部添加
   * @param value
   */
  public void addFirst(int value) throws IllegalAccessException {
    insert(0,value);
  }

  /**
   * 遍历链表1
   * @param consumer 要执行的操作
   */
  public void loop1(Consumer<Integer> consumer){
    Node p = head.next;
    while(p != null){
      consumer.accept(p.value);
      p = p.next;
    }
  }

  /**
   * 遍历链表2
   * @param consumer
   */
  public void loop2(Consumer<Integer> consumer){
    for(Node p = head.next;p != null;p = p.next){
      consumer.accept(p.value);
    }
  }

  private  Node findLast(){
    Node p ;
    for (p = head; p.next != null;p = p.next){}
    return p;
  }

  /**
   * 向链表尾部添加
   * @param value
   */
  public void addLast(int value){
    Node last = findLast();
    last.next = new Node(value,null);
  }

  private Node findNode(int index){
    int i = -1;
    for(Node p = head;p != null;p = p .next,i++){
      if(i== index){
        return  p;
      }
    }
    return null;//未找到
  }

  /**
   * 根据索引查找
   *
   * @param index 索引
   * @return 找到, 返回该索引位置节点的值
   * @throws IllegalArgumentException 找不到, 抛出 index 非法异常
   */
  public int get(int index) throws IllegalArgumentException {
    Node node = findNode(index);
    if (node == null) {
      throw illegalIndex(index);
    }
    return node.value;
  }

  private IllegalArgumentException illegalIndex(int index) {
    return new IllegalArgumentException(
        String.format("index [%d] 不合法%n", index));
  }

  public void insert(int index,int value) throws IllegalAccessException{
    Node prev =findNode(index-1);
    if(prev ==  null){
      throw illegalIndex(index);
    }
    prev.next = new Node(value,prev.next);
  }

  /**
   * 删除第一个
   * @throws IllegalArgumentException
   */
  public void removeFirst() throws  IllegalArgumentException{
    remove(0);
  }

  public void remove(int index) throws IllegalArgumentException{
    Node prev =findNode(index - 1 );//上一个节点
    if(prev == null){
      throw illegalIndex(index);
    }
    Node removed = prev.next;
    if(removed == null){
      throw illegalIndex(index);
    }
    prev.next = removed.next;
  }

}
