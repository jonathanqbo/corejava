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

package bq.demo.concurrency.lock.exchanger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 9:00:32 PM
 *
 */
public class Producer implements Runnable{

	private List<String> buffer;
	
	private Exchanger<List<String>> exchanger;
	
	public Producer(Exchanger<List<String>> exchanger) {
		this.exchanger = exchanger;
		buffer = new LinkedList<String>();
	}

	@Override
	public void run() {
		
		for(int i = 0; i < 10; i++){
			fillBuffer("value" + i);

			// notify consumer
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("producer send buffer: time " + i);
		}
		
	}
	
	private void fillBuffer(String feed){
		for(int i = 0; i < 10; i++)
			buffer.add(feed + i);
	}

}
