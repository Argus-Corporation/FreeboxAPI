package net.argus.api.freebox.api.language;

public enum AvalaibleLanguages {
	
	FRA, ENG;
	
	public static AvalaibleLanguages getLanguages(String language) {
		for(AvalaibleLanguages as : values())
			if(language.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return null;
	}
	
}
