/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b> </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 * Create at 3:54:41 PM Dec 7, 2016
 * 
 */

public class TimerUsage {

	public static void main(String[] args) {
		// do task after 1000 milliseconds
		Timer timer1 = new Timer();
		CountTask task1 = new CountTask("task1");
		timer1.schedule(task1, 1000);
		
		try {
			// task1 will only run once
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		timer1.cancel();
		
		// fix delay: do task one after one after first 1000 milliseconds delay
		Timer timer2 = new Timer();
		CountTask task2 = new CountTask("task2");
		timer2.schedule(task2, 1000, 1000);
		
		try {
			// task2 will run 2 times since once takes 2 seconds
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		timer2.cancel();
		
		// fix rate: do task in fixed every 1000 milliseconds after first 1000 milliseconds delay
		Timer timer3 = new Timer();
		CountTask task3 = new CountTask("task3");
		timer3.scheduleAtFixedRate(task3, 1000, 1000);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		timer3.cancel();
	}
	
	/**
	 * 2 second task 
	 */
	static class CountTask extends TimerTask {

		private String name;
		private AtomicInteger count = new AtomicInteger(1);
		
		public CountTask(String name) {
			this.name = name;
		}
		
		
		@Override
		public void run() {
			System.out.println("task " + name + " is doing " + count.get());
			count.incrementAndGet();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			
		}
		
	}
	
}
