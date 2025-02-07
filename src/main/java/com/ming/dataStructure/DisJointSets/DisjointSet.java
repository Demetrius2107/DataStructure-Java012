package com.ming.dataStructure.DisJointSets;

/**
 * @author Shukun.Li
 */
public class DisjointSet {

    // 存储每个节点的父节点
   private int[] parent;

   // 存储每个集合的秩
   private int[] rank;

   // 构造函数 初始化集合
   public DisjointSet(int n){
       parent = new int[n];
       rank = new int[n];
       for (int i = 0; i < n; i++) {
           // 每个节点的父节点初始化为自身
           parent[i] = i;
           // 每个集合的秩初始化为0
           rank[i] = 0;
       }
   }

   // 查找操作、带路径压缩
   public int find(int x){
       if(parent[x] != x){
           // 路径压缩、将x直接连接到根节点
           parent[x] = find(parent[x]);
       }
       return parent[x];
   }

   // 合并操作、带按秩合并
   public void union(int x,int y){
       int rootX = find(x);
       int rootY = find(y);

       // 如果x和y已经在同一个集合中,直接返回
       if(rootX == rootY){
           return;
       }

       if(rank[rootX] < rank[rootY]){
           parent[rootX] = rootY;
       } else if(rank[rootX] > rank[rootY]){
           parent[rootY] = rootX;
       }else {
           parent[rootY] = rootX;
           rank[rootX]++;
       }
   }

   // 判断两个元素是否在同一集合中
    public boolean isConnected(int x,int y){
        return find(x) == find(y);
    }




}
