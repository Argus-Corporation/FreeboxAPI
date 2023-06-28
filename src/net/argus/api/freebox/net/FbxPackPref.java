package net.argus.api.freebox.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.argus.api.freebox.api.language.AvalaibleLanguages;
import net.argus.api.freebox.api.rrd.TypeRRD;
import net.argus.cjson.value.CJSONString;

public class FbxPackPref {
	
	public static FbxPackage getAuthorizationPackage(String appId, String appName, String appVersion) throws UnknownHostException {
		String deviceName = InetAddress.getLocalHost().getHostName();

		return FbxPackageBuilder.getPack(new FbxPackEntry("app_id", appId), new FbxPackEntry("app_name", appName),
				new FbxPackEntry("app_version", appVersion), new FbxPackEntry("device_name", deviceName));
	}
	
	public static FbxPackage getSessionPackage(String appId, String password) {
		return FbxPackageBuilder.getPack(new FbxPackEntry("app_id", appId), new FbxPackEntry("password", password));
	}

	public static FbxPackage getAirMediaPlayPackage() {
		return FbxPackageBuilder.getPack(new FbxPackEntry("action", "start"), new FbxPackEntry("media_type", "audio"),
				new FbxPackEntry("media", "https://www.kozco.com/tech/LRMonoPhase4.mp3"), new FbxPackEntry("password", "1111"));
	}
	
	public static FbxPackage getChangeLanguagePackage(AvalaibleLanguages lang) {
		return FbxPackageBuilder.getPack(new FbxPackEntry("lang", lang.toString().toLowerCase()));
	}

	public static FbxPackage getRRDPackage(TypeRRD type, int dateStart, int dateEnd, int precision, String ... fields) {
		List<FbxPackEntry> entrys = new ArrayList<FbxPackEntry>();
		entrys.add(new FbxPackEntry("db", type.toString().toLowerCase()));
		if(dateStart > -1)
			entrys.add(new FbxPackEntry("date_start", dateStart));
		if(dateEnd > -1)
			entrys.add(new FbxPackEntry("date_end", dateEnd));
		if(precision > -1)
			entrys.add(new FbxPackEntry("precision", precision));
		
		if(fields != null && fields.length > 0) {
			CJSONString[] vals = new CJSONString[fields.length];
			for(int i = 0; i < vals.length; i++)
				vals[i] = new CJSONString(fields[i]);
			entrys.add(new FbxPackEntry("fields", vals));
		}

		return FbxPackageBuilder.getPackArray((FbxPackEntry[]) entrys.toArray(new FbxPackEntry[] {}));
	}
	
	public static FbxPackage getUpdateCall(boolean new0) {
		return FbxPackageBuilder.getPack(new FbxPackEntry("new", new0));
	}
	
	public static FbxPackage getUpdateVoiceMail(boolean read) {
		return FbxPackageBuilder.getPack(new FbxPackEntry("read", read));
	}


}
