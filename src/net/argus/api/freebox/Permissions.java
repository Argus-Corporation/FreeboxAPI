package net.argus.api.freebox;

public enum Permissions {
	
	SETTINGS, CONTACTS, CALLS, EXPLORER, DOWNLOADER, PARENTAL, PVR, PROFILE, TV, WDO, CAMERA, PLAYER, HOME, VM;

	public static Permissions getPermission(String perm) {
		for(Permissions as : values())
			if(perm.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return null;
	}
	
}
