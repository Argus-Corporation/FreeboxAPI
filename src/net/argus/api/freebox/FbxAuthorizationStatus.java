package net.argus.api.freebox;

import java.io.IOException;

import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.util.ThreadManager;

public class FbxAuthorizationStatus {
	
	private AuthorizationStatus status = AuthorizationStatus.UNKNOWN;
	
	private int trackId;
	private FreeboxProperties fbxProperties;
	
	public FbxAuthorizationStatus(FreeboxProperties fbxProperties, int trackId) {
		this.fbxProperties = fbxProperties;
		this.trackId = trackId;
	}
	
	private FbxRequestReturn waitAuthorization() throws IOException {
		FbxRequestReturn ret = null;
		while(status != AuthorizationStatus.GRANTED && status != AuthorizationStatus.TIMEOUT && status != AuthorizationStatus.DENIED) {
			ret = FbxIO.sendGET("/login/authorize/" + trackId, null, fbxProperties);
			if(!ret.isSuccess())
				continue;
			
			status = AuthorizationStatus.getAuthorizationStatus(ret.getResult().getString("status"));
			if(status== AuthorizationStatus.GRANTED)
				break;

			ThreadManager.sleep(1000);
		}
		return ret;
	}
	
	public AuthorizationStatus getStatus() {
		return status;
	}
	
	public FbxRequestReturn startFinding() throws IOException {
		return waitAuthorization();
	}

}
