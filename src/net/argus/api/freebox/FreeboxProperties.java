package net.argus.api.freebox;

import net.argus.api.freebox.net.FbxRequestReturn;

public class FreeboxProperties {
	
	private String boxModelName;
	private String apiBaseUrl;
	private int httpsPort;
	private String deviceName;
	private boolean httpsAvailable;
	private String boxModel;
	private String apiDomain;
	private String uid;
	private String apiVersion = "5.0";
	private String deviceType;
	
	public FreeboxProperties(FbxRequestReturn apiVersionReturn) {
		boxModelName = apiVersionReturn.getRespons().getString("box_model_name");
		apiBaseUrl = apiVersionReturn.getRespons().getString("api_base_url");
		httpsPort = apiVersionReturn.getRespons().getInt("https_port");
		deviceName = apiVersionReturn.getRespons().getString("device_name");
		httpsAvailable = apiVersionReturn.getRespons().getBoolean("https_available");
		boxModel = apiVersionReturn.getRespons().getString("box_model");
		apiDomain = apiVersionReturn.getRespons().getString("api_domain");
		uid = apiVersionReturn.getRespons().getString("uid");
		//apiVersion = apiVersionReturn.getRespons().getString("api_version");
		deviceType = apiVersionReturn.getRespons().getString("device_type");
	}
	
	public String getBoxModelName() {
		return boxModelName;
	}

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public int getHttpsPort() {
		return httpsPort;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public boolean isHttpsAvailable() {
		return httpsAvailable;
	}

	public String getBoxModel() {
		return boxModel;
	}

	public String getApiDomain() {
		return apiDomain;
	}

	public String getUid() {
		return uid;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getDeviceType() {
		return deviceType;
	}

}
