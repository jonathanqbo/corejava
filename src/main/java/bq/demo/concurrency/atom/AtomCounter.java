/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.concurrency.atom;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b> Thread-safe version </b>
 *
 * <p> i++ is not atom operation, using AtomInteger provided by concurrency framework to make it thread-safe</p>
 *
 * @author Jonathan.q.bo@gmail.com
 * 
 * Create at 3:18:51 PM Dec 7, 2016
 * 
 */

public class AtomCounter {

	private AtomicInteger count = new AtomicInteger(0);
	
	public void increase() {
		count.incrementAndGet();
	}
	
	public void decrease() {
		count.decrementAndGet();
	}
	
	public int value() {
		return count.get();
	}
	
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(20);
		
		Counter badCounter = new Counter();
		AtomCounter goodCounter = new AtomCounter();

		// run 20 tasks synchronizedly and compare the difference between thread-safe and un-thread-safe counter 
		for(int i = 0; i < 20; i++) {
			pool.scheduleAtFixedRate(
					()-> {
						badCounter.increase();
						goodCounter.increase();},
					0, 100, TimeUnit.NANOSECONDS);
			
		}
		
		try {
			pool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pool.shutdown();
		
		// unthreadsafe result will be much less than thread-safe version
		System.out.println(String.format("Correct Count: %d, bad count : %d",  goodCounter.value(), badCounter.value() ));
		
	}
	
}

/**
 * Unthreadsafe version
 */
class Counter {
	
	private int count = 0;
	
	public void increase() {
		count++;
	}
	
	public void decrease() {
		count--;
	}
	
	public int value() {
		return count;
	}
	
}