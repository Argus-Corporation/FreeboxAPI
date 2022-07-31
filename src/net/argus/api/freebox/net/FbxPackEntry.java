package net.argus.api.freebox.net;

public class FbxPackEntry {
	
	private String name;
	private Object value;
	
	public FbxPackEntry(String name, Object value) {
		if(!(value instanceof Integer) && !(value instanceof String) && !(value instanceof Boolean))
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
