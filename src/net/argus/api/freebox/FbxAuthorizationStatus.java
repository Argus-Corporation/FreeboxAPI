package net.argus.api.freebox;

import java.io.IOException;

import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.util.ThreadManager;

public class FbxAuthorizationStatus extends Thread {
	
	private AuthorizationStatus status = AuthorizationStatus.UNKNOWN;
	
	private int trackId;
	private FreeboxProperties fbxProperties;
	
	public FbxAuthorizationStatus(FreeboxProperties fbxProperties, int trackId) {
		this.fbxProperties = fbxProperties;
		this.trackId = trackId;
	}
	
	@Override
	public void run() {
		try {waitAuthorization();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void waitAuthorization() throws IOException {
		while(status != AuthorizationStatus.GRANDTED) {
			ThreadManager.sleep(1000);
			FbxRequestReturn ret = FbxIO.sendGET("/login/authorize/" + trackId, null, fbxProperties);
			if(!ret.isSuccess())
				continue;
			
			status = AuthorizationStatus.getAuthorizationStatus(ret.getResult().getString("status"));
		}
	}
	
	public AuthorizationStatus getStatus() {
		return status;
	}

}
