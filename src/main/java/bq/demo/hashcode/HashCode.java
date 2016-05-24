package bq.demo.hashcode;

import java.util.Objects;

import org.junit.Test;

/**
 * RULE: 
 * 
 * If you are going to override the one of the methods( ie equals() and
 * hashCode() ) , you have to override the both otherwise it is a violation of
 * contract made for equals() and hashCode().
 * 
 * @see Object#hashCode()
 * 
 * 1. Same object should always return same hash code
 * 2. Two objects which are equals by {@link Object#equals(Object)} should have same hash code
 * 
 * @author qibo
 *
 */
public class HashCode {

	@Test
	public void test(){
		// Same value object, will get different hashcode
		Object1 o1 = new Object1(100, "value");
		System.out.println(o1.hashCode());
		Object1 o11 = new Object1(100, "value");
		System.out.println(o11.hashCode());
		
		Object2 o2 = new Object2(100, "value");
		System.out.println(o2.hashCode());
		
		// Same value object, will get same hashcode
		Object3 o3 = new Object3(100, "value");
		System.out.println(o3.hashCode());
		Object3 o31 = new Object3(100, "value");
		System.out.println(o31.hashCode());
	}
	
}

/**
 * No hashCode implementation
 */
class Object1{
	private int value;
	private String name;
	
	public Object1(int value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

/**
 * Generate hashCode by Eclipse 
 */
class Object2{
	private int value;
	private String name;
	
	public Object2(int value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Object2 other = (Object2) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}

/**
 * Implements hashCode by {@link Objects#hash(Object...)}
 */
class Object3{
	private int value;
	private String name;
	
	
	public Object3(int value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(value,name);
	}
	
}
