package com.ming.dataStructure.BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树的第一种简单实现，其中key值为固定int类型
 */
public class BSTTree1 {

  BSTNode root;//根节点

  static class BSTNode {

    int key;
    Object value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int key) {
      this.key = key;
    }

    public BSTNode(int key, Object value) {
      this.key = key;
      this.value = value;
    }

    public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
      this.key = key;
      this.value = value;
      this.right = right;
      this.left = left;
    }

  }

  //递归实现
 /*
  public Object get(int key){
    return doGet(root,key);
  }

  private Object doGet(BSTNode node,int key){
    if (node == null){
      return null;//没找到
    }
    if(key  < node.key){
      return doGet(node.left,key);
    } else if (node.key < key){
      return doGet(node.right,key);
    } else {
      return node.value;
    }
  }*/

  public Object get(int key) {
    BSTNode node = root;
    while (node != null) {
      if (key < node.key) {
        node = node.left;
      } else if (node.key < key) {
        node = node.right;
      } else {
        return node.value;
      }
    }
    return null;
  }

  /**
   * 查看最小关键字对应值
   *
   * @return 关键字对应的值
   */
  public Object min() {
    return min(root);
  }

  private Object min(BSTNode node) {
    if (node == null) {
      return null;
    }
    BSTNode p = node;
    while (p.left != null) {
      p = p.left;
    }
    return p.value;
  }

  /**
   * 查找最大关键字对应值
   *
   * @return 关键字对应的值
   */
  public Object max() {
    return max(root);
  }

  private Object max(BSTNode node) {
    if (node == null) {
      return null;
    }
    BSTNode p = node;
    while (p.right != null) {
      p = p.right;
    }
    return p.value;
  }

  /**
   * 促农畜关键字和对应值
   *
   * @param key   关键字
   * @param value 值
   */
  public void put(int key, Object value) {
    root = doPut(root, key, value);
  }

  private BSTNode doPut(BSTNode node, int key, Object value) {
    if (node == null) {
      return new BSTNode(key, value);
    } else if (node.key < key) {
      node.right = doPut(node.right, key, value);
    } else {
      node.value = value;
    }
    return node;
  }

  /**
   * 查找关键字的前任值
   * @param key 关键字
   * @return 前任值
   */
  public Object predecessor(int key) {
    BSTNode p = root;
    BSTNode ancestorFromLeft = null;
    while (p != null) {
      if (key < p.key) {
        p = p.left;
      } else if (p.key < key) {
        ancestorFromLeft = p;
        p = p.right;
      } else {
        break;
      }
    }
    //没有找到节点
    if(p == null){
      return null;
    }
    //找到节点 情况1 节点有左子树，此时前任是左子树的最大值
    if(p.left != null){
      return max(p.left);
    }
    //找到节点 情况2  左子树没有节点，若是离他最最近，自左而来的祖先就是他的前任
    return ancestorFromLeft != null ? ancestorFromLeft : null;
  }

  public Object successor(int key){
    BSTNode p = root;
    BSTNode ancestorFromRight = null;
    while(p != null){
      if(key < p.key){
        ancestorFromRight = p;
        p = p.left;
      } else if (p.key < key){
        p = p.right;
      } else {
        break;
      }
    }
    //没找到节点
    if(p == null){
      return null;
    }
    //找到节点 情况1 节点有右子树，此时后任是右子树的最小值
    if(p.right != null){
      return min(p.right);
    }
    //找到节点 情况2 节点没有右子树，若是离他最近的，自右而来的祖先就是后任
    return ancestorFromRight != null ? ancestorFromRight.value : null;
  }

  public Object remove(int key){
    BSTNode p = root;
    BSTNode parent = null;
    while( p!= null){
      if(key < p.key){
        parent = p;
        p = p.left;
      } else  if(p.key < key){
        parent = p;
        p = p.right;
      } else {
        break;
      }
    }
    if(p == null){
      return null;
    }
    //删除操作
    if(p.left == null){
      shift(parent,p,p.right);//情况1
    } else if(p.right == null){
      shift(parent,p,p.left);//情况2
    } else{
      //情况4
      //4.1被删除节点找后继
      BSTNode s = p.right;
      BSTNode sParent = p;//后继父亲
      while(s.left != null){
        sParent = s;
        s = s.left;
      }
      //后继节点即为s
      if(sParent != p){
        //不相邻
        //4.2 删除和后继不相邻，处理后继的后事
        shift(sParent,s,s.right);
        s.right = p.right;
      }
      //4.3后继取代被删除节点
      shift(parent,p,s);
      s.left =  p.left;
    }
    return p.value;
  }



  private void shift(BSTNode parent,BSTNode deleted,BSTNode child){
    if(parent == null){
      root = child;
    } else if (deleted == parent.left){
      parent.left = child;
    } else{
      parent.right = child;
    }
  }

  public List<Object> less(int key){
    //key = 6
    ArrayList<Object> result = new ArrayList<>();
    BSTNode p = root;
    LinkedList<BSTNode> stack = new LinkedList<>();
    while(p != null || !stack.isEmpty()){
      if(p != null){
        stack.push(p);
        p = p.left;
      } else {
        BSTNode pop = stack.pop();
        //处理值
        if(pop.key < key){
          result.add(pop.value);
        } else {
          break;
        }
        p = pop.right;
      }
    }
    return result;
  }

  //找 > key 的所有value
  public List<Object> greater(int key){
    ArrayList<Object> result = new ArrayList<>();
    BSTNode p = root;
    LinkedList<BSTNode> stack = new LinkedList<>();
    while(p != null || !stack.isEmpty() ){
      if(p!=null){
        stack.push(p);
        p = p.right;
      } else{
        BSTNode pop = stack.pop();
        if(pop.key > key){
          result.add(pop.value);
        } else{
          break;
        }
        p = pop.left;
      }
    }
    return result;
  }

  //
  public List<Object> betwwen(int key1,int key2) {
    ArrayList<Object> result = new ArrayList<>();
    BSTNode p = root;
    LinkedList<BSTNode> stack = new LinkedList<>();
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        stack.push(p);
        p = p.left;
      } else {
        BSTNode pop = stack.pop();
        //处理值
        if (pop.key >= key1 && pop.key <= key2) {
          result.add(pop.value);
        } else if (pop.key > key2) {
          break;
        }
        p = pop.right;
      }
    }
  return  result;
  }

}
