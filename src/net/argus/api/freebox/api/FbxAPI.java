package net.argus.api.freebox.api;

import java.io.IOException;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.util.debug.Debug;

public abstract class FbxAPI {
	
	private static AirMedia airMedia = null;
	
	public static void createAirMediaSession(FreeboxProperties properties, FreeboxSession session) throws IOException {
		airMedia = new AirMedia(properties, session);
	}
	
	public static AirMedia getAirMedia() {
		if(airMedia == null) {
			Debug.log("AirMedia isn't created");
			return null;
		}
		return airMedia;
	}
	
	public abstract boolean validAccess(FreeboxSession session);

}
