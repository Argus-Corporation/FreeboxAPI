package net.argus.api.freebox.api.rrd;

public enum TypeRRD {
	
	NET, TEMP, DSL, SWITCH;
	
	public static TypeRRD getType(String type) {
		for(TypeRRD as : values())
			if(type.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return null;
	}

}
