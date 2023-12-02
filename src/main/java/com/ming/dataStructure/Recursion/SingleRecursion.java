package com.ming.dataStructure.Recursion;

public class SingleRecursion {

  private static int f(int n){
    if(n==1){
      return 1;
    }
    return n * f(n-1);
  }

}
