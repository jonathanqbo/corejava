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

package bq.demo.concurrency.lock.reentrantlock.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 10:21:55 AM
 *
 */
public class Main {

	public static void main(String[] args){
		Buffer buffer = new Buffer(10);
		
		int length = 10;
		Producer[] producers = new Producer[length];
		Consumer[] consumers = new Consumer[length];
		
		for(int i = 0; i < length; i++){
			producers[i] = new Producer("producer" + i, buffer);
			consumers[i] = new Consumer("consuer" + i, buffer);
		}
		
		ExecutorService threadpool = Executors.newFixedThreadPool(length*2);
		for(int i = 0; i < length; i++){
			threadpool.submit(consumers[i]);
			threadpool.submit(producers[i]);
		}
		
		threadpool.shutdown();
	}
	
}
