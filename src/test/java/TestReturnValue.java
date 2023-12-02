import org.junit.Test;


public class TestReturnValue {

  public static void main(String[] args) {
    int size = 1;
    int capacity = 2;
  //  System.out.println(Compare(size,capacity));
  boolean number = Compare(size,capacity);
    System.out.println(number);
  }

  public static boolean Compare(int size, int capacity){
    return size == capacity;
  }
}
