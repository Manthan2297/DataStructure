package algs11;

import org.junit.Test;
import static org.junit.Assert.*;

public class TreeLTest {

    @Test
    public void test1() {
        TreeL t = new TreeL();
        t.toDLL();
        assertEquals(t.root,null);
    }

    @Test
    public void test2(){
        TreeL t = new TreeL(new Node(1));
        t.toDLL();
        assertEquals(t.root.value,1);
        assertEquals(t.root.left,null);
        assertEquals(t.root.right,null);
    }

    @Test
    public void test3(){
        TreeL t = new TreeL(new Node(2));
        t.root.left = new Node(1);
        t.root.right = new Node(3);
        t.toDLL();
        assertEquals(t.root.value,1);
        assertEquals(t.root.left.value,3);
        assertEquals(t.root.right.value,2);
        assertEquals(t.root.right.right.value,3);
        assertEquals(t.root.right.right.right.value,1);

    }

    
}
