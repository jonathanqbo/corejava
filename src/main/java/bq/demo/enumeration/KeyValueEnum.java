package bq.demo.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>  </b>
 *
 * <p> show how to use enum and how to loop on enum values</p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Mar 6, 2014 3:53:43 PM
 *
 */
public enum KeyValueEnum {

	RED("red", "#FF0000", "Standard Red"),
	BLACK("black","#000000", "totally black"),
	WHITE("white", "#FFFFFF", "totally white");
	
	private String key;	
	private String value;
	private String description;
	
	
	private static Map<String, KeyValueEnum> name2enumMap = null;

	/**
	 * must be private
	 * 
	 * @param key
	 * @param value
	 * @param description
	 */
	private KeyValueEnum(String key, String value, String description){
		this.key = key;
		this.value = value;
		this.description = description;
	}
	
	/** 
	 * must be static
	 * 
	 * @param name
	 * @return
	 */
	public static KeyValueEnum get(String name){
		if(name2enumMap == null){
			name2enumMap = new HashMap<String, KeyValueEnum>();
			KeyValueEnum[] values = values();
			// loop on enum
			for (KeyValueEnum keyValueEnum : values) {
				name2enumMap.put(keyValueEnum.getKey(), keyValueEnum);
			}
		}
		
		return name2enumMap.get(name);
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "key:" + key + ", value:" + value + ", description:" + description;
	}
	
}
