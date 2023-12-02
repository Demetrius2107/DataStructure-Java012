package com.ming.dataStructure.stack;

import java.util.LinkedList;

public class E01Leetcode120 {

  public int evalRPN(String[] tokens) {
    LinkedList<Integer> numbers = new LinkedList<>();
    for (String t : tokens) {
      switch (t) {
        case "+" -> {
          Integer b = numbers.pop();
          Integer a = numbers.pop();
          numbers.push(a + b);
        }
        case "-" -> {
          Integer b = numbers.pop();
          Integer a = numbers.pop();
          numbers.push(a - b);
        }
        case "*" -> {
          Integer b = numbers.pop();
          Integer a = numbers.pop();
          numbers.push(a * b);
        }
        case "/" -> {
          Integer b = numbers.pop();
          Integer a = numbers.pop();
          numbers.push(a / b);
        }
        default -> numbers.push(Integer.parseInt(t));
      }
    }
    return numbers.pop();
  }
}
