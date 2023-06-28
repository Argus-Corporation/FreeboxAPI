package net.argus.api.freebox.api;

import java.io.IOException;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;

public abstract class FbxAPI {
	
	private static AirMedia airMedia = null;
	private static Call call = null;
	private static Language language = null;
	private static RRD rrd = null;
	
	protected FreeboxProperties properties;
	protected FreeboxSession session;
	
	FbxAPI(FreeboxProperties properties, FreeboxSession session) throws IOException {
		this.properties = properties;
		this.session = session;
		
		if(!validAccess(session)) {
			Debug.log("You aren't allowed to access to this API", Info.ERROR);
			return;
		}
		init();
	}
	
	protected abstract void init() throws IOException;
	
	public static void setAirMediaSession(FreeboxProperties properties, FreeboxSession session) throws IOException {
		airMedia = new AirMedia(properties, session);
	}
	
	public static void setCallSession(FreeboxProperties properties, FreeboxSession session) throws IOException {
		call = new Call(properties, session);
	}
	
	public static void setLanguageSession(FreeboxProperties properties, FreeboxSession session) throws IOException {
		language = new Language(properties, session);
	}
	
	public static void setRRDSession(FreeboxProperties properties, FreeboxSession session) throws IOException {
		rrd = new RRD(properties, session);
	}
	
	public static AirMedia getAirMedia() {
		if(airMedia == null) {
			Debug.log("AirMedia isn't created");
			return null;
		}
		return airMedia;
	}
	
	public static Call getCall() {
		if(call == null) {
			Debug.log("Call isn't created");
			return null;
		}
		return call;
	}
	
	public static Language getLanguage() {
		if(language == null) {
			Debug.log("Language isn't created");
			return null;
		}
		return language;
	}
	
	public static RRD getRRD() {
		if(rrd == null) {
			Debug.log("RRD isn't created");
			return null;
		}
		return rrd;
	}
	
	public abstract boolean validAccess(FreeboxSession session);

}
