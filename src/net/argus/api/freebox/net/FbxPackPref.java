package net.argus.api.freebox.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FbxPackPref {
	
	public static FbxPackage getAuthorizationPackage(String appId, String appName, String appVersion) throws UnknownHostException {
		String deviceName = InetAddress.getLocalHost().getHostName();

		return FbxPackageBuilder.getPack(new FbxPackEntry("app_id", appId), new FbxPackEntry("app_name", appName),
				new FbxPackEntry("app_version", appVersion), new FbxPackEntry("device_name", deviceName));
	}
	
	public static FbxPackage getSessionPackage(String appId, String password) {
		return FbxPackageBuilder.getPack(new FbxPackEntry("app_id", appId), new FbxPackEntry("password", password));
	}

	public static FbxPackage getAirMediaPlayPackage() {
		return FbxPackageBuilder.getPack(new FbxPackEntry("action", "start"), new FbxPackEntry("media_type", "audio"),
				new FbxPackEntry("media", "https://www.kozco.com/tech/LRMonoPhase4.mp3"), new FbxPackEntry("password", "1111"));
	}

}
