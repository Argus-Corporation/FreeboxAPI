package net.argus.api.freebox;

public enum SessionPermissions {
	
	SETTINGS, CONTACTS, CALLS, EXPLORER, DOWNLOADER, PARENTAL, PVR, PROFILE, TV, WDO, CAMERA, PLAYER, HOME, VM;

	public static SessionPermissions getPermission(String perm) {
		for(SessionPermissions as : values())
			if(perm.toUpperCase().equals(as.toString().toUpperCase()))
				return as;
		return null;
	}
	
}
