package net.argus.api.freebox.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.api.freebox.api.airmedia.AirMediaCapabilitie;
import net.argus.api.freebox.api.airmedia.AirMediaReceiver;
import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxPackPref;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.Array;

@Deprecated
public class AirMedia extends FbxAPI {
	
	private List<AirMediaReceiver> receivers = new ArrayList<AirMediaReceiver>();
	
	AirMedia(FreeboxProperties properties, FreeboxSession session) throws IOException {
		super(properties, session);
	}
	
	@Override
	protected void init() throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/airmedia/receivers/", session, properties);
		if(!ret.isSuccess())
			return;
		
		Array arr = ret.getResultArray();
		for(int i = 0; i < ret.getResultArray().length(); i++)
			receivers.add(new AirMediaReceiver(arr.get(i).getString("name"), AirMediaCapabilitie.getCapabilitie(arr.get(i).getObject("capabilities")), arr.get(i).getBoolean("password_protected")));

	}
	
	public void play() throws IOException {
		FbxRequestReturn ret = FbxIO.sendPOST("/airmedia/receivers/" + receivers.get(0).getName() + "/", FbxPackPref.getAirMediaPlayPackage(), session, properties);
		System.out.println(ret);
	}
	
	public List<AirMediaReceiver> getReceivers() {
		return receivers;
	}

	@Override
	public boolean validAccess(FreeboxSession session) {
		return true;
	}

}
