package bq.demo.enumeration;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jul 21, 2014 10:25:38 AM
 *
 */
public enum SingleValueEnum {
	
	Red("red"),
	Black("black"),
	White("white");

	private String value;
	
	private SingleValueEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
