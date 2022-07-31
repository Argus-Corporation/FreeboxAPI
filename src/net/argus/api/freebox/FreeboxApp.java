package net.argus.api.freebox;

public class FreeboxApp {
	
	private String appId;
	private String appToken;
	private String challenge;
	private String passwordSalt;
	
	public FreeboxApp(String appId, String appToken, String challenge, String passwordSalt) {
		this.appId = appId;
		this.appToken = appToken;
		this.challenge = challenge;
		this.passwordSalt = passwordSalt;
	}
	
	public String getAppId() {
		return appId;
	}

	public String getAppToken() {
		return appToken;
	}

	public String getChallenge() {
		return challenge;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}
	
	@Override
	public String toString() {
		return  appToken + " @ " + challenge + " : " + passwordSalt;
	}
	
}
