/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <b> </b>
 *
 * <p> The difference with normal thread is : Daemon thread won't stop main thread exit</p>
 * <p> NOTE: Daemon thread will be stopped when main thread exited
 *
 * @author Jonathan.q.bo@gmail.com
 * Create at 11:13:22 AM Dec 8, 2016
 * 
 */

public class DaemonThread {
	
	public static void main(String[] args) {
		Thread daemonThread = new Thread(() -> {
			System.out.println("I'm daemon");
			
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					System.out.println("Daemon is breathing ...");
				}
			}, 0, 1000);
		}); 
		
		daemonThread.setDaemon(true);
		daemonThread.start();
		
		// main thread holds 5 seconds and exit
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		System.out.println("Main exited");
	}

}
