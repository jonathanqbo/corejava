package bq.demo.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 10, 2015 6:09:34 PM
 *
 */
public class QueueUsage {

	/**
	 * LinkedList Queue
	 */
	@Test
	public void test(){
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < 10; i++)
			queue.add(i);
		
		System.out.println("queue.peek():" + queue.peek());
		
		System.out.print("queue.poll():");
		for(int i = 0; i < 10; i++)
			System.out.print(queue.poll()+",");
		
		System.out.println();
	}
	
	/**
	 * LinkedList Deque
	 */
	@Test
	public void test2(){
		Deque<Integer> dequeue = new LinkedList<>();
		
		//
		for(int i = 0; i < 10; i++){
			dequeue.addLast(i);
		}
		
		System.out.println("FIFO: deque.addLast(), deque.pollFirst()");
		while(!dequeue.isEmpty())
			System.out.print(dequeue.pollFirst()+",");
		
		System.out.println();
		
		//
		for(int i = 0; i < 10; i++){
			dequeue.addFirst(i);
		}
		
		System.out.println("FIFO: deque.addFirst(), deque.pollLast()");
		while(!dequeue.isEmpty())
			System.out.print(dequeue.pollLast()+",");
		
		System.out.println();
		
		//
		for(int i = 0; i < 10; i++){
			dequeue.addLast(i);
		}
		
		System.out.println("LIFO: deque.addLast(), deque.pollLast()");
		while(!dequeue.isEmpty())
			System.out.print(dequeue.pollLast()+",");
		
		System.out.println();
	}
	
	
}
