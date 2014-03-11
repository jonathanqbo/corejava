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

package bq.demo.concurrency.lock.semaphore.multiple;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * <b>  </b>
 *
 * <p> more powerful than ReentrantLock</p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 6, 2014 4:23:56 PM
 *
 */
public class Resource {
	
	// allow 5 threads visit this resource at the same time
	private Semaphore semaphore = new Semaphore(5);

	private String name;
	
	public Resource(String name){
		this.name = name;
	}
	
	public void visit(){
		try {
			// get 
			semaphore.acquire();

			Thread.sleep((long) (Math.random()*1000));
			System.out.printf("[%s]: visited at %s \n", Thread.currentThread().getName(), DateFormat.getTimeInstance(DateFormat.LONG).format(new Date()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			// release
			semaphore.release();
		}
		
	}

	@Override
	public String toString() {
		return "Resource[" + name + "]";
	}
}
