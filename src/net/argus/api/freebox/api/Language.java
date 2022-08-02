package net.argus.api.freebox.api;

import java.io.IOException;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.api.freebox.api.language.AvalaibleLanguage;
import net.argus.api.freebox.api.language.AvalaibleLanguages;
import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxPackPref;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.util.debug.Debug;

public class Language extends FbxAPI {
	
	private AvalaibleLanguage avalaibleLanguage;
	
	Language(FreeboxProperties properties, FreeboxSession session) throws IOException {
		super(properties, session);
	}

	@Override
	protected void init() throws IOException {
		avalaibleLanguage = AvalaibleLanguage.getAvalaibleLanguage(properties, session);
	}
	
	public void reload() throws IOException {
		init();
		Debug.log("Language is reloaded: " + avalaibleLanguage.getCurrentLanguage() + " (current)");
	}
	
	public boolean changeLanguage(AvalaibleLanguages language) throws IOException {
		if(language == null)
			return false;
		
		if(!avalaibleLanguage.isAvalaible(language)) {
			Debug.log("Language \"" + language + "\" isn't supported");
			return false;
		}
		
		FbxRequestReturn ret = FbxIO.sendPOST("/lang/", FbxPackPref.getChangeLanguagePackage(language), session, properties);
		return ret.isSuccess();
	}
	
	public AvalaibleLanguage getAvalaibleLanguage() {
		return avalaibleLanguage;
	}
	
	@Override
	public boolean validAccess(FreeboxSession session) {
		return true;
	}

}
