/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <b> Consumer / Producer by using wait(), notifyAll() </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * Create at 4:56:11 PM Dec 8, 2016
 * 
 */

public class WaitNotifyAll {

	public static void main(String[] args) {
		List<Producer> producers = new LinkedList<>();
		List<Consumer> consumers = new LinkedList<>();
		BQQueue<String> queue = new BQQueue<String>(5);
		
		for( int i = 0; i < 5; i++ ) {
			producers.add(new Producer(queue));
			consumers.add(new Consumer(queue));
		}
		
		producers.forEach(t -> t.start());
		consumers.forEach(t -> t.start());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {}
		
		producers.forEach(t -> {
			try {
				t.join();
			} catch (Exception e) {}
		});
		
		consumers.forEach( t -> {
			try {
				t.join();
			} catch (Exception e) {}
		});
		
		System.out.println("final queue size: " + queue.size());
	}
	
}

class Producer extends Thread {
	private BQQueue<String> queue;
	
	public Producer( BQQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		int i = 0;
		while ( i < 10 ) {
			String value = getName() + "_msg_" + i;
			queue.offer(value);
			System.out.println(getName() + " produce " + value);
			
			i++;
		}
	}
}

class Consumer extends Thread {
	
	private BQQueue<String> queue;
	
	public Consumer( BQQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		int i = 0;
		while ( i < 10 ) {
			String result = queue.poll();
			System.out.println(getName() + " consume " + result);
			
			i++;
		}
	}
}

class BQQueue<E> {
	
	private Object WRITE = new Object();
	private Object READ = new Object();
	
	private int maxSize = 10;
	private Queue<E> queue = new LinkedList<E>();
	
	public BQQueue(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public synchronized void offer(E e) {
		if ( queue.size() >= maxSize ) {
			try {
				System.out.println("I'm full and waiting...");
				wait();
			} catch (InterruptedException e1) {}
		}
		
		queue.offer(e);
		notifyAll();
	};
	
	public synchronized E poll() {
		if ( queue.size() == 0 ) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		E result = queue.poll();
		notifyAll();
		return result;
	}
	
	public int size() {
		return queue.size();
	}
}
