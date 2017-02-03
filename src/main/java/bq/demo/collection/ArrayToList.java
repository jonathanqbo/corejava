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

package bq.demo.collection;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 10, 2014 11:18:13 AM
 *
 */
public class ArrayToList {
	
	/**
	 * Example output:
	 * 
	 * [I@675d3402,
	 * 1,2,3,4,5,6,7,8,
	 */
	@Test
	public void primitiveToList() {
		// NOTE: for primitive array, Arrays.asList will treat whole array as one element of List
		int[] array = {1, 2, 3, 4, 5, 6,7 ,8};
		List<int[]> list = Arrays.asList(array);
		list.forEach(v -> System.out.print(v + ","));
		System.out.println();
		
		// for Object array, Arrays.asList will work as expected
		Integer[] array2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> list2 = Arrays.asList(array2);
		list2.forEach(v -> System.out.print(v + ","));
		System.out.println();
		//list2.forEach(System.out::println);
	}

	@Test
	public void tranformArrayToList(){
		String[] array = genArray();
		
		// array to list
		List<String> list = Arrays.asList(array);
		printList(list);
		
		// list to array
		String[] array2 = list.toArray(new String[0]);
		printArray(array2);
	}
	
	private void printArray(String[] array2) {
		System.out.println();
		System.out.print("[");
		for(String value : array2){
			System.out.print(value + ",");
		}
		System.out.print("]");
	}

	private void printList(List<String> list) {
		System.out.println();
		System.out.print("[");
		for(String value : list){
			System.out.print(value + ",");
		}
		System.out.print("]");
	}

	private String[] genArray(){
		String[] array = new String[100];
		for(int i = 0; i < array.length; i++){
			array[i] = String.valueOf(i);
		}
		
		return array;
	}
	
}
