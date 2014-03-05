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

package bq.demo.other.url;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/**
 * <b> how to get the URL of a resource </b>
 *
 * <p> URL -> URI and URI -> URL </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 5, 2014 11:20:27 AM
 *
 */
public class URLMaker {

	@Test
	public void byFile(){
		URL url = null;
		try {
			// relative to project root path
			url = new File("src/main/java/bq/demo/other/url/resource.txt").toURI().toURL();
			System.out.println("by file:" + url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		assertResult(url,true);
	}
	
	@Test
	public void byClassLoader(){
		URL url = URLMaker.class.getResource("resource.txt");
		System.out.println("by classloader:" + url);
		assertResult(url,true);
		
		// can use package name to avoid multiple same name resource
		url = URLMaker.class.getResource("/bq/demo/other/url/resource.txt");
		System.out.println("by classloader:" + url);
		assertResult(url,true);
		
		// cannot find without the beginning "/"
		url = URLMaker.class.getResource("bq/demo/other/url/resource.txt");
		System.out.println("by classloader:" + url);
		assertResult(url,false);
		
		// cannot find by this way
		url = URLMaker.class.getResource("/url/resource.txt");
		System.out.println("by classloader:" + url);
		assertResult(url,false);
	}
	
	private void assertResult(URL url,boolean isExist){
		if(url == null && !isExist)
			return;
		
		try {
			File file = new File(url.toURI());
			Assert.assertEquals(true,file.exists());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}
