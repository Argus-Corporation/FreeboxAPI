package net.argus.api.freebox;

public enum AuthorizationStatus {
	
	UNKNOWN, PENDING, TIMEOUT, GRANTED, DENIED;
	
	public static AuthorizationStatus getAuthorizationStatus(String status) {
		for(AuthorizationStatus as : values())
			if(status.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return UNKNOWN;
	}

}
