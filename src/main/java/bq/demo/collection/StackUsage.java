package bq.demo.collection;

import java.util.Stack;

import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 10, 2015 5:27:16 PM
 *
 */
public class StackUsage {

	@Test
	public void test(){
		Stack<Integer> stack = new Stack<>();
		
		// stack push
		for(int i = 0; i < 10; i++)
			stack.push(i);
		
		// stack peek
		System.out.println("stack.peek(): " + stack.peek());
		
		// stack pop
		System.out.print("stack.pop():");
		while(!stack.empty())
			System.out.print(stack.pop()+",");
			
		
	}
	
}
