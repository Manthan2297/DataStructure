package algs11;
import stdlib.*;
import java.util.Stack;

class Node {
    Node left, right;
    int value;
    public Node(int value){
        this.value = value;
    }
    public Node(int value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

}

interface Marked {}

class MarkedNode extends Node implements Marked {
    public MarkedNode(int value){
        super(value);
    }
}

public class TreeL{
    Node root;
    public TreeL(Node root){
        this.root = root;
    }
    
    public TreeL() {
    root = null;
    }

    /** Returns true if the given target is in the binary tree. 
     * Uses a recursive helper. */
    
    public boolean lookup(int data) { 
        return(lookup(root, data));
    }

    /** Recursive lookup -- given a node, recur
     * down searching for the given data. */
    private boolean lookup(Node node, int data) {
        if (node==null) { return(false);}
        if (data==node.value) { return(true);
        }
        else if (data<node.value) {
                    return(lookup(node.left, data));
                }
                else { 
                    return(lookup(node.right, data));
                }
    }

    /**
     * Inserts the given data into the binary tree.
     * Uses a recursive helper.
     * */
    public void insert(int data) {
        root = insert(root, data);
    }
    
    /** Recursive insert -- given a node pointer, recur down and
    insert the given data into the tree. Returns the new
    node pointer (the standard way to communicate
    a changed pointer back to the caller).
    */
    private Node insert(Node node, int data) {
        if (node==null) {
            node = new Node(data);
        }
        else {
            if (data <= node.value) {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }
        return(node); // in any case, return the new pointer to the caller
    }
    
    

    public boolean isBST1(){
        return isBST1(root);
    }

    private static boolean isBST1(Node node){
        if(node == null) return true;
        if(node.left != null && maxVal(node.left) > node.value) return false;
        if(node.right != null && minVal(node.right) < node.value) return false;
        return isBST1(node.left) && isBST1(node.right);
    }

    public boolean isBST2(){
        return isBST2(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public static boolean isBST2(Node n,int min, int max){
        if(n == null) return true;
        if(n.value < min || n.value > max) return false;
        return isBST2(n.left,min,n.value) && isBST2(n.right,n.value,max);
    }

    private static Node create(int N){
        if (N == 0) return null;
        else 
           return new Node(N,create(N-1),null);
    }

    public static double timeTrial(int N) {
		long T = 10; // number of tests
		double sum = 0;
        TreeL tree = new TreeL(create(N));
		for (long t = 0; t < T; t++) {
			Stopwatch s = new Stopwatch();
			tree.isBST2();
			sum +=  s.elapsedTime();
		}
		return sum/T;
	}

	private static final int MIN = 100;
	private static final int MAX = 20000;
	//private static final long MAX = Long.MAX_VALUE/2;
	public static void main(String[] args) {
		double prev = timeTrial(MIN);
		for (int N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format("%19d %9.3f %5.1f\n", N, time, time/prev);
			prev = time;
		}
	}
    public int size() {
        return(size(root));
       }

    private static int size(Node node) {
        return ((node == null) ? 0 : size(node.left) + size(node.right) +1);

    }

    public void toDLL(){
        
    }

    
    public int maxVal(){
        return maxVal(root);
    }

    // Preondition: node is not null
    // returns the maximum value in the tree ( not assumed to be a BST)
    //postcondition: the tree is unchanged
    private static int maxVal(Node node){
        if (node == null) return Integer.MIN_VALUE;
        else 
            return Math.max(node.value,Math.max(maxVal(node.left),maxVal(node.right)));
    }

    public int minVal(){
        return minVal(root);
    }

    // Preondition: node is not null
    // returns the maximum value in the tree
    //postcondition: the tree is unchanged
    private static int minVal(Node node){
        if (node == null) return Integer.MIN_VALUE;
        else 
            return Math.min(node.value,Math.min(maxVal(node.left),maxVal(node.right)));
    }

    // write maxxval using a while loop
    private static int maxVal2(Node node){
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }

    public void inorder(){
        inorder(root);
    }

    public static void inorder(Node node){
        if(node == null){
            return;
        }
        inorder(node.left);
        StdOut.println(node.value);
        inorder(node.right);
    }

    public void inorder2(){
        inorderIterative(root);
    }

  

    public static void inorderIterative(Node node){
        Stack<Node> stack = new Stack<Node>();
        
        stack.push(node);
        
          // Stack has the nodes that have NOT been processed yet
        stack.push(node);
        while (!stack.isEmpty()){
            Node n = stack.pop();
            if (n instanceof MarkedNode){
                StdOut.println(n.value);
            } else {
                if (n.right != null) stack.push(n.right);
                stack.push(new MarkedNode(n.value));
                if (n.left != null) stack.push(n.left); 
            }
        }


           


        
    }


    public int height() {
        return height(root);
    }

    private static int height(Node node) {
        return ((node == null) ? 0 : Math.max(height(node.left), height(node.right)) +1);
       
    }

      
    public static TreeL build123(){
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        return new TreeL(root);
    }

    public static TreeL build123a(){
        TreeL t = new TreeL();
        t.insert(2);
        t.insert(1);
        t.insert(3);
        return t;
    }

    

    
}
