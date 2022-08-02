package net.argus.api.freebox;

public class FreeboxApp {
	
	private String appId;
	private String appToken;
	
	public FreeboxApp(String appId, String appToken) {
		this.appId = appId;
		this.appToken = appToken;
	}
	
	public String getAppId() {
		return appId;
	}

	public String getAppToken() {
		return appToken;
	}

	@Override
	public String toString() {
		return  appId + " @[" + appToken + "]";
	}
	
}
