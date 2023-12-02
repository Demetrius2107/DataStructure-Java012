package com.ming.dataStructure.stack;

public class E01Leetcode20 {

  public boolean isValid(String s){
    ArrayStack<Character> stack = new ArrayStack<>(s.length()/2 + 1);
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(c == '('){
        stack.push(')');
      } else if(c == '['){
        stack.push(']');
      } else if(c == '{'){
        stack.push('}');
      } else {
        if(!stack.isEmpty() && stack.peek() == c){
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }


}
