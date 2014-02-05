/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * <b> Demo of JDK Deflater and Inflater</b>
 * 
 * <p>
 * 
 * </P>
 * 
 * @see Deflater
 * @see Inflater
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class JDKDeflater {
	
	/**
	 * compress source file and generate new zip file
	 * 
	 * @param filepath
	 * @param tofilepath
	 */
	public void deflateFile(String filepath, String tofilepath){
		byte[] zipedfilebytes = deflaterFile(filepath);
		
		//
		try {
			generateFileFromBytes(tofilepath, zipedfilebytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * deflate file 
	 * 
	 * @param filepath
	 * @return
	 */
	public byte[] deflaterFile(String filepath){
		File file = new File(filepath);
		try {
			byte[] filebytes = getBytesFromFile(file);
			byte[] zipfilebytes = deflater(filebytes);
			return zipfilebytes;
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private byte[] getBytesFromFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		int byteslength = fis.available();
		byte[] bytes = new byte[byteslength];
		fis.read(bytes);
		return bytes;
	}
	
	/**
	 * inflate file 
	 * 
	 * @param filebytes
	 * @param filefullpath
	 */
	public void inflateFile(byte[] filebytes, String filefullpath){
		byte[] unpackedfilebytes = inflater(filebytes);
		try {
			generateFileFromBytes(filefullpath, unpackedfilebytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateFileFromBytes(String filefullpath, byte[] unpackedfilebytes)
			throws IOException, FileNotFoundException {
		File file = new File(filefullpath);
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(unpackedfilebytes);
		fos.flush();
		fos.close();
	}
	
	
	
	/**
	 * deflate object
	 * 
	 * @param obj, must be Serializable
	 * @return
	 */
	public byte[] deflaterObject(Object obj){
		try {
			byte[] objbytes = getBytesFromObject(obj);
			return deflater(objbytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private byte[] getBytesFromObject(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.close();
		
		byte[] objbytes = bos.toByteArray();
		return objbytes;
	}

	/**
	 * inflate object
	 * 
	 * @param zipdata
	 * @return
	 */
	public Object inflaterObject(byte[] zipdata){
		byte[] orgdata = inflater(zipdata);
		
		try {
			return getObjectFromBytes(orgdata);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Object getObjectFromBytes(byte[] objbytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(objbytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

	/**
	 * deflate bytes
	 * 
	 * @param orgdata
	 * @return
	 */
	public byte[] deflater(byte[] orgdata){
		Deflater df = new Deflater(Deflater.BEST_COMPRESSION);
		df.setInput(orgdata);
		df.finish();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(orgdata.length);
	    
	    // Compress the data
	    byte[] buf = new byte[1024];
	    while (!df.finished()) {
	        int count = df.deflate(buf);
	        bos.write(buf, 0, count);
	    }
	    try {
	        bos.close();
	    } catch (IOException e) {
	    }
	    
	    // Get the compressed data
	    byte[] compressedData = bos.toByteArray();
	    
		return compressedData;
	}
	
	/**
	 * inflate bytes
	 * 
	 * @param zipdata
	 * @return
	 */
	public byte[] inflater(byte[] zipdata){
		Inflater decompressor = new Inflater();
	    decompressor.setInput(zipdata);
	    
	    // Create an expandable byte array to hold the decompressed data
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(zipdata.length);
	    
	    // Decompress the data
	    byte[] buf = new byte[1024];
	    while (!decompressor.finished()) {
	        try {
	            int count = decompressor.inflate(buf);
	            bos.write(buf, 0, count);
	        } catch (DataFormatException e) {
	        }
	    }
	    try {
	        bos.close();
	    } catch (IOException e) {
	    }
	    
	    // Get the decompressed data
	    byte[] decompressedData = bos.toByteArray();
	    
		return decompressedData;
	}
	
}
