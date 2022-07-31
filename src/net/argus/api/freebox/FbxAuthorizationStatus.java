package net.argus.api.freebox;

import java.io.IOException;

import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.instance.Instance;
import net.argus.util.ThreadManager;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;

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
		try {
			@SuppressWarnings("unused")
			FbxRequestReturn ret = waitAuthorization();
			if(status == AuthorizationStatus.TIMEOUT) {
				Debug.log("The user did not confirmed the authorization within the given time", Info.ERROR);
			}else if(status == AuthorizationStatus.DENIED) {
				Debug.log("The user denied the authorization request", Info.ERROR);

			}else if(status == AuthorizationStatus.GRANTED) {
				
			}
		}catch(IOException e) {e.printStackTrace();}
	}
	
	private FbxRequestReturn waitAuthorization() throws IOException {
		FbxRequestReturn ret = null;
		while(status != AuthorizationStatus.GRANTED && status != AuthorizationStatus.TIMEOUT && status != AuthorizationStatus.DENIED) {
			ThreadManager.sleep(1000);
			ret = FbxIO.sendGET("/login/authorize/" + trackId, null, fbxProperties);
			if(!ret.isSuccess())
				continue;
			
			status = AuthorizationStatus.getAuthorizationStatus(ret.getResult().getString("status"));

		}
		return ret;
	}
	
	public AuthorizationStatus getStatus() {
		return status;
	}
	
	public FbxRequestReturn startFinding(boolean newThread) throws IOException {
		if(newThread)
			Instance.startThread(this, Main.STATUS);
		else
			return waitAuthorization();
		
		return null;
	}

}
