/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * <b> </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 3:38:15 PM Dec 17, 2016
 * 
 */

public class ClonableUsage {

	@Test
	public void copyUnclonableObj () {
		ClonableObj co = new ClonableObj("value");
		try {
			ClonableObj co1 = (ClonableObj) co.clone();
			assertEquals(co1, co);
			assertFalse(co1==co);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		// will get compile error "The method clone() from the type Object is not visible"
//		UnclonableObj uco = new UnclonableObj();
//		uco.clone();
	}
	
}

class UnclonableObj {
	
}

class ClonableObj implements Cloneable{
	private String value;
	
	public ClonableObj( String value) {
		this.value = value;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new ClonableObj(this.value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ClonableObj other = (ClonableObj) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
