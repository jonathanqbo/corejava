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

package bq.demo.concurrency.lock.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> CyclicBarrier usage </b>
 *
 * <p> A synchronization aid that allows a set of threads to all wait for each other to reach 
 * a common barrier point. CyclicBarriers are useful in programs involving a fixed sized party 
 * of threads that must occasionally wait for each other. The barrier is called cyclic 
 * because it can be re-used after the waiting threads are released.</p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 5:06:18 PM
 *
 */
public class Main {

	public static void main(String[] args){
		// after all participants arrived, meeting begin
		CyclicBarrier cb = new CyclicBarrier(10, new Meeting());
		
		Participant[] participants = new Participant[10];
		for(int i = 0; i < 10 ; i++){
			participants[i] = new Participant("participant" + i, cb);
		}
		
		ExecutorService threadpool = Executors.newCachedThreadPool();
		for(Participant participant : participants){
			threadpool.submit(participant);
		}
		
		threadpool.shutdown();
	}
	
}
