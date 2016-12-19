/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * <b> Three ways to implement Fibonacci </b>
 *
 * <p> Get the n Fibonacci number
 * 
 * <li> by ForkJoin RecursiveTask
 * <li> by two pointer
 * <li> by recursive
 * 
 * </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 10:46:38 PM Dec 18, 2016
 * 
 */

public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		
		System.out.println(f.recursiveV1(10));
		System.out.println(f.recursiveV2(10));
		System.out.println(f.recursiveV3(10));
	}
	
	/* simple recursive, bad performance */
	public int recursiveV1(int n) {
		if ( n == 1)
			return 0;
		
		if ( n == 2)
			return 1;
		
		return recursiveV1(n - 2) + recursiveV1(n - 1);
	}
	
	/* two pointer, better performance */
	public int recursiveV2(int n) {
		if ( n == 1)
			return 0;
		
		if ( n == 2)
			return 1;
		
		int f1 = 0, f2 = 1;
		int i = 3;
		while(i < n) {
			int tmp = f1 + f2;
			f1 = f2;
			f2 = tmp;
			
			i++;
		}
		
		return f1 + f2;
	}
	
	/* using ForkJoin RecursiveTask */
	public int recursiveV3(int n) {
		ForkJoinPool pool = new ForkJoinPool();
		FibonacciTask task = new FibonacciTask(n);
		pool.execute(task);
		
		try {
			pool.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		pool.shutdown();
		
		if(task.isCompletedNormally())
			try {
				return task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		
		return -1;
	}
	
}

class FibonacciTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 8183460726622555304L;

	private int n = 0;
	
	public FibonacciTask(int n) {
		this.n = n;
	}
	
	@Override
	protected Integer compute() {
		if ( n == 1)
			return 0;
		if ( n == 2)
			return 1;
		
		FibonacciTask f1 = new FibonacciTask(n-2);
		FibonacciTask f2 = new FibonacciTask(n-1);
		
		invokeAll(f1, f2);

		try {
			return f1.get() + f2.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
