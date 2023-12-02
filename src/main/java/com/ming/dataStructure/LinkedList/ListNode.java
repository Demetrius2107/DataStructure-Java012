package com.ming.dataStructure.LinkedList;

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode(int val,ListNode next){
    this.val = val;
    this.next = next;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(64);
    sb.append("[");
    ListNode p = this;
    while(p.next != null){
      sb.append(p.val);
      if(p.next != null){
        sb.append(",");
      }
      p = p.next;
    }
    sb.append("]");
    return sb.toString();
  }

  public static ListNode of(int... element){
    if(element.length == 0){
      return null;
    }
    ListNode p = null;
    for(int i = element.length - 1; i >= 0;i--){
      p = new ListNode(element[i],p);
    }
    return p;
  }
}
