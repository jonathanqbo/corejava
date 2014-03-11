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

package bq.demo.concurrency.collection.concurrentlinkeddeque;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 10:04:02 PM
 *
 */
public class Consumer implements Runnable{

	private String name;
	
	private ConcurrentLinkedDeque<String> deque;
	
	public Consumer(String name, ConcurrentLinkedDeque<String> deque) {
		this.name = name;
		this.deque = deque;
	}

	@Override
	public void run() {
		int i = 0;
		while(i < 10){
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// value maybe null, because it's unblock, it will not wait for producer
			String value = deque.poll();
			System.out.printf("[%s] %s consume %s \n", Thread.currentThread().getName(), name, value );
			
			if(value != null)
				i++;
		}
	}

}
