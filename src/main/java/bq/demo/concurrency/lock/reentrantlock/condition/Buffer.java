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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 10:20:31 AM
 *
 */
public class Buffer {

	private int maxSize;
	private List<String> buffer;
	
	private Lock lock;
	private Condition consumer;
	private Condition producer;
	
	public Buffer(int maxSize){
		this.maxSize = maxSize;
		buffer = new LinkedList<String>();
		
		lock = new ReentrantLock();
		consumer = lock.newCondition();
		producer = lock.newCondition();
	}
	
	public void insert(String value){
		lock.lock();
		
		try{
			// wait in a loop
			while(buffer.size() == maxSize){
				System.out.printf("[%s]: buffer is full \n", Thread.currentThread().getName());
				producer.await();
			}
			
			buffer.add(value);
			
			// notify
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public String get(){
		lock.lock();
		
		String value = null;
		try{
			// consumer wait
			while(buffer.size() == 0){
				System.out.printf("[%s]: buffer is empty \n", Thread.currentThread().getName());
				consumer.await();
			}
			
			value = buffer.remove(0);
			
			// notify producer
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
		return value;
	}
	
}
