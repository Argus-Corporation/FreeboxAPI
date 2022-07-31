package net.argus.api.freebox.api.airmedia;

public enum AirMediaCapabilities {
	
	PHOTO, AUDIO, VIDEO, SCREEN;
	
	public static AirMediaCapabilities getCapabilitie(String capabilitie) {
		for(AirMediaCapabilities as : values())
			if(capabilitie.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return null;
	}
	
}
