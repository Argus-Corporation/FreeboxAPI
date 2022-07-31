package net.argus.api.freebox;

import java.io.IOException;

import net.argus.api.freebox.net.FbxRequestReturn;

public class Main {

	public static void main(String[] args) throws IOException {
		Freebox freebox = Freebox.getLocalFreebox();
		//System.out.println(FbxIO.sendPOST("/login/authorize/", null, FbxPackPref.getAuthorizationPackage("argus_freebox", "FreeboxAPI", "0.0"), freebox.getProperties()).getResult());
		FbxRequestReturn autRes = new FbxRequestReturn("{\"success\": true, \"result\": {\"app_token\": \"imkrfZoNRKEzMCIHjn+s3e4iezkqhnHu2RT3x8a3/EAKd+ppUXkBLGQ8kg2uT+jn\", \"track_id\": 0}}");
		
		FbxAuthorizationStatus.waitAuthorization(freebox.getProperties(), autRes.getResult().getInt("track_id"));
		
	
	}

}
