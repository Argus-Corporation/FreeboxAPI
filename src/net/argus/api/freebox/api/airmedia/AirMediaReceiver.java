package net.argus.api.freebox.api.airmedia;

public class AirMediaReceiver {
	
	private String name;
	private AirMediaCapabilitie capabilitie;
	private boolean passwordProtect;
	
	public AirMediaReceiver(String name, AirMediaCapabilitie capabilitie, boolean passwordProtect) {
		this.name = name;
		this.capabilitie = capabilitie;
		this.passwordProtect = passwordProtect;
	}

	public String getName() {
		return name;
	}

	public AirMediaCapabilitie getCapabilitie() {
		return capabilitie;
	}

	public boolean isPasswordProtect() {
		return passwordProtect;
	}
	
	@Override
	public String toString() {
		return name + "@" + capabilitie;
	}

}
