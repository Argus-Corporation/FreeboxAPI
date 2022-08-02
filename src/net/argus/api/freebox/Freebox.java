package net.argus.api.freebox;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxPackPref;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;

public class Freebox {
	
	private FreeboxProperties properties;
	
	public Freebox(FbxRequestReturn apiVersionReturn) {
		this.properties = new FreeboxProperties(apiVersionReturn);
	}
		
	public static Freebox getLocalFreebox() throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET(new URL("http://mafreebox.freebox.fr/api_version"), null);

		if(ret == null)
			return null;
		
		return new Freebox(ret);
	}
	
	@SuppressWarnings("incomplete-switch")
	public FreeboxApp createNewApp(String appId, String appName, String appVersion) throws IOException {
		FbxRequestReturn autRes = FbxIO.sendPOST("/login/authorize/", FbxPackPref.getAuthorizationPackage(appId, appName, appVersion), null, properties);

		if(!autRes.isSuccess())
			return null;
		
		FbxAuthorizationStatus status = new FbxAuthorizationStatus(properties, autRes.getResult().getInt("track_id"));
		FbxRequestReturn statusRet = status.startFinding();
		
		if(statusRet == null || !statusRet.isSuccess()) 
			return null;
		
		switch(status.getStatus()) {
			case TIMEOUT:
				Debug.log("The user did not confirmed the authorization within the given time", Info.ERROR);				
				return null;
	
			case DENIED:
				Debug.log("The user denied the authorization request", Info.ERROR);
				return null;
		}
		
		
		return new FreeboxApp(appId, autRes.getResult().getString("app_token"));
	}
	
	public FreeboxSession openSession(FreeboxApp app) throws IOException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		FbxRequestReturn log = FbxIO.sendGET("/login/", null, properties);
		if(!log.isSuccess())
			return null;
		
		if(log.getResult().getBoolean("logged_in")) {
			Debug.log("You are alredy connected", Info.ERROR);
			return null;
		}
		String challenge = log.getResult().getString("challenge");
		String password = FreeboxSignature.calculateRFC2104HMAC(challenge, app.getAppToken());
		
		FbxRequestReturn ses = FbxIO.sendPOST("/login/session/", FbxPackPref.getSessionPackage(app.getAppId(), password), null, properties);

		if(!ses.isSuccess())
			return null;
		
		return new FreeboxSession(ses.getResult().getString("session_token"), SessionPermission.getPermissions(ses));
	}
	
	public boolean closeSession(FreeboxSession session) throws IOException {
		FbxRequestReturn ret = FbxIO.sendPOST("/login/logout/", null, session, properties);
		session.close();
		return ret.isSuccess();
	}
	
	public FreeboxProperties getProperties() {
		return properties;
	}

}
