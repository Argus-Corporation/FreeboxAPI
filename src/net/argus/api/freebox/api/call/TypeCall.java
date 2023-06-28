package net.argus.api.freebox.api.call;

public enum TypeCall {
	
	MISSED, ACCEPTED, OUTGOING;
	
	public static TypeCall getType(String type) {
		for(TypeCall tc : values())
			if(type.toUpperCase().equals(tc.toString().toUpperCase()))
				return tc;
		return null;
	}

}
