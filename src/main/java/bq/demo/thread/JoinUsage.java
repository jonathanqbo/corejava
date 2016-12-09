/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <b> </b>
 *
 * <p> start 10 threads, and use join() to wait all them done </p>
 * <p> NOTE: It's illegal to start a Thread twice, see {@link Thread#start()}
 *
 * @author Jonathan.q.bo@gmail.com
 * Create at 10:45:23 AM Dec 8, 2016
 * 
 */

public class JoinUsage {

	public static void main(String[] args) {
		JoinUsage.waitAllDone();
		JoinUsage.oneAfterOne();
	}
	
	/* output:
	Thread-6 done!
	Thread-5 done!
	Thread-0 done!
	Thread-1 done!
	Thread-2 done!
	Thread-3 done!
	Thread-7 done!
	Thread-4 done!
	Thread-8 done!
	Thread-9 done!
	all tasks done!
	*/
	public static void waitAllDone() {
		List<Thread> tasks = new LinkedList<Thread>();
		
		for(int i = 0; i < 10; i++) {
			tasks.add(new Thread(()-> {
				// less than 5 seconds task
				try {
					Thread.sleep(new Random().nextInt(5)*1000);
				} catch (Exception e) {
				}
				
				System.out.println(Thread.currentThread().getName() + " done!");
			}));
		}
		
		// start each task
		tasks.forEach(t -> t.start());
		
		// wait for all tasks ending
		tasks.forEach(t -> {
				try {
					t.join();
				} catch (Exception e) {}
			});
		
		System.out.println("all tasks done!");
	}
	
	/* output:
		Thread-10 done!
		Thread-11 done!
		Thread-12 done!
		Thread-13 done!
		Thread-14 done!
		Thread-15 done!
		Thread-16 done!
		Thread-17 done!
		Thread-18 done!
		Thread-19 done!
		all tasks done!
	 */
	public static void oneAfterOne() {
		List<Thread> tasks = new LinkedList<Thread>();
		
		for(int i = 0; i < 10; i++) {
			tasks.add(new Thread(()-> {
				// less than 5 seconds task
				try {
					Thread.sleep(new Random().nextInt(5)*1000);
				} catch (Exception e) {
				}
				
				System.out.println(Thread.currentThread().getName() + " done!");
			}));
		}
		
		// one after one
		tasks.forEach(t -> {
			t.start();
			
			try {
				t.join();
			} catch (Exception e) {}
		});
		
		System.out.println("all tasks done!");
	}
	
}
