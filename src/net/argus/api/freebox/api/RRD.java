package net.argus.api.freebox.api;

import java.io.IOException;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.api.freebox.api.rrd.TypeRRD;
import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxPackPref;
import net.argus.api.freebox.net.FbxRequestReturn;

public class RRD extends FbxAPI {

	RRD(FreeboxProperties properties, FreeboxSession session) throws IOException {
		super(properties, session);
	}

	@Override
	protected void init() throws IOException {}
	
	public FbxRequestReturn getRRD(TypeRRD type, int dateStart, int dateEnd, int precision, String ... fields) throws IOException {
		return FbxIO.sendPOST("/rrd/", FbxPackPref.getRRDPackage(type, dateStart, dateEnd, precision, fields), session, properties);
	}

	@Override
	public boolean validAccess(FreeboxSession session) {
		return true;
	}

}
