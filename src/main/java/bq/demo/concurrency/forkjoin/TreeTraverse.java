/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.concurrency.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * <b> BinaryTree Traverse </b>
 *
 * <p> 
 *   <li> by ForkJoin RecursiveAction
 *   <li> by recursive pre-order Traverse
 * </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 4:35:12 PM Dec 17, 2016
 * 
 */

public class TreeTraverse {

	public static void main(String[] args) {
		Node root = new Node(0);
		
		TreeTraverse ra = new TreeTraverse();
		ra.build(root, 0);
		
		// no order guarantee, order is unpredicted
		System.out.println( "===========> RecursiveAction visitor");
		BqBinaryTreeVisitor visitor = new BqBinaryTreeVisitor(root);
		// this is synchronized
		visitor.invoke();
		
		// pre order traverse, order is predicted
		System.out.println( "\n===========> pre order traverse");
		BqBinaryTreeTraverse traverser = new BqBinaryTreeTraverse();
		traverser.preOrder(root);
	}
	
	public void build(Node node, int level) {
		if ( level == 3 ) 
			return;
		
		Node left = new Node(node.value*2 + 1);
		Node right = new Node(node.value*2 + 2);
		
		node.left = left;
		node.right = right;
		
		build(left, level+1);
		build(right, level + 1);
	}
	
}

class BqBinaryTreeVisitor extends RecursiveAction {
	
	private static final long serialVersionUID = -9193327410557948383L;

	private Node root;
	
	public BqBinaryTreeVisitor(Node root) {
		this.root = root;
	}

	@Override
	protected void compute() {
		if ( root == null )
			return;
		
		// visit root
		System.out.print(root.value + ", ");
		
		// visit left/right child
		// this is asynchronized
		invokeAll(new BqBinaryTreeVisitor(root.left),
				new BqBinaryTreeVisitor(root.right));
	}
	
}

class BqBinaryTreeTraverse {
	
	public void preOrder(Node root) {
		if( root == null ) 
			return;
		
		System.out.print(root.value + ", ");
		
		preOrder(root.left);
		preOrder(root.right);
	}
	
}

class Node {
	Node left;
	Node right;
	int value;
	
	public Node(int value) {
		this.value = value;
	}
}
