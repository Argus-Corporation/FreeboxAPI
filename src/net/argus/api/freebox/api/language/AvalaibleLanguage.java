package net.argus.api.freebox.api.language;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.value.CJSONValue;

public class AvalaibleLanguage {
	
	private List<AvalaibleLanguages> languages = new ArrayList<AvalaibleLanguages>();
	private AvalaibleLanguages current;
	
	public AvalaibleLanguage(List<AvalaibleLanguages> language, AvalaibleLanguages current) {
		this.languages = language;
		this.current = current;
	}
	
	public List<AvalaibleLanguages> getAvalaibleLanguages() {
		return languages;
	}
	
	public AvalaibleLanguages getCurrentLanguage() {
		return current;
	}
	
	public boolean isAvalaible(AvalaibleLanguages lang) {
		return languages.contains(lang);
	}
	
	public static AvalaibleLanguage getAvalaibleLanguage(FreeboxProperties properties, FreeboxSession session) throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/lang/", session, properties);
		if(!ret.isSuccess())
			return null;
		
		AvalaibleLanguages curr = AvalaibleLanguages.getLanguages(ret.getResult().getString("lang"));

		List<AvalaibleLanguages> langs = new ArrayList<AvalaibleLanguages>();
		for(CJSONValue obj : ret.getResult().getArray("avalaible").getArray())
			langs.add(AvalaibleLanguages.getLanguages(obj.getValue().toString()));
		
		return new AvalaibleLanguage(langs, curr);
	}
	

}
