package net.argus.api.freebox.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import net.argus.api.freebox.FbxPackage;

public class FbxPackPref {
	
	public static FbxPackage getAuthorizationPackage(String appId, String appName, String appVersion) throws UnknownHostException {
		String deviceName = InetAddress.getLocalHost().getHostName();

		return FbxPackageBuilder.getPack(new FbxPackEntry("app_id", appId), new FbxPackEntry("app_name", appName),
				new FbxPackEntry("app_version", appVersion), new FbxPackEntry("device_name", deviceName));
	}

}