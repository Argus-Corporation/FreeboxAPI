package net.argus.api.freebox;

import java.io.IOException;
import java.net.URL;

import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxRequestReturn;

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
	
	public FreeboxProperties getProperties() {
		return properties;
	}

}
