package datasture.binarysearchtree;

import com.ming.dataStructure.Tree.BinaryTree;
import org.junit.Test;

public class BinaryTreeTest {

    //前序遍历建树
    Integer[] arr = { 1, 2, 4, null, null, 5, null, null, 3, null, null };
    BinaryTree tree = new BinaryTree(arr);

    @Test
    public void preOrderTest(){
        tree.preOrderRecur();

    }
}
