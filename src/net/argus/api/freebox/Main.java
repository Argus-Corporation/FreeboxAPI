package net.argus.api.freebox;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import net.argus.api.freebox.api.AirMedia;
import net.argus.api.freebox.api.FbxAPI;
import net.argus.instance.Instance;

public class Main {
	
	public static final Instance MAIN = new Instance("fbx-main");
	public static final Instance STATUS = new Instance("fbx-status");

	public static void main(String[] args) throws IOException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Freebox freebox = Freebox.getLocalFreebox();
		FreeboxApp app = freebox.createNewApp("argus_freebox", "FreeboxAPI", "0.0");
		FreeboxSession ses = freebox.openSession(app);

		FbxAPI.createAirMediaSession(freebox.getProperties(), ses);
		
		AirMedia media = FbxAPI.getAirMedia();
		media.play();
		freebox.closeSession(ses);
	}

}
