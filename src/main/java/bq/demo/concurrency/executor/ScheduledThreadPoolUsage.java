/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bq.demo.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 6, 2014 12:23:33 AM
 *
 */
public class ScheduledThreadPoolUsage {

	@Test
	public void doScheduledThreadPool(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
		
		final ScheduledFuture<?> schedulefuture1 = scheduledThreadPool.scheduleAtFixedRate(new OneSecondTask("task1"), 1, 2,TimeUnit.SECONDS);
		final ScheduledFuture<?> schedulefuture2 = scheduledThreadPool.scheduleAtFixedRate(new OneSecondTask("task2"), 1, 3,TimeUnit.SECONDS);
		final ScheduledFuture<?> schedulefuture3 = scheduledThreadPool.scheduleWithFixedDelay(new OneSecondTask("task3"), 1, 5,TimeUnit.SECONDS);
		
		scheduledThreadPool.scheduleAtFixedRate(new TimePrinterTask(), 0, 1, TimeUnit.SECONDS);

		// cancel all task after 30 secondes
		scheduledThreadPool.schedule(new Runnable() {
			
			@Override
			public void run() {
				schedulefuture1.cancel(true);
				System.out.println("task1 canceled");
				
				schedulefuture2.cancel(true);
				System.out.println("task2 canceled");
				
				schedulefuture3.cancel(true);
				System.out.println("task3 canceled");
				
			}
		}, 30, TimeUnit.SECONDS);
		
		// will terminate after 60 seconds because all the task are not canceled not finished
		try {
			boolean isFinished = scheduledThreadPool.awaitTermination(60, TimeUnit.SECONDS);
			Assert.assertEquals(isFinished, false);
		} catch (InterruptedException e) {
		}
		
		//
		Assert.assertEquals(true, schedulefuture1.isCancelled());
		// consider it as done normally
		Assert.assertEquals(true, schedulefuture1.isDone());
	}
	
}
