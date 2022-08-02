package net.argus.api.freebox.net;

import net.argus.cjson.value.CJSONValue;

public class FbxPackEntry {
	
	private String name;
	private Object value;
	
	public FbxPackEntry(String name, Object value) {
		if(!(value instanceof Integer) && !(value instanceof String) && !(value instanceof Boolean) && !(value instanceof CJSONValue[]))
			return;
		
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + " = " + value;
	}
	
}
