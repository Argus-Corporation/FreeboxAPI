package net.argus.api.freebox;

public class FreeboxSession {
	
	private String sessionToken;
	private Permission perm;
	
	private boolean close;
	
	public FreeboxSession(String sesstionToken, Permission perm) {
		this.sessionToken = sesstionToken;
		this.perm = perm;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public Permission getPerm() {
		return perm;
	}
	
	public boolean isClose() {
		return close;
	}
	
	public void close() {
		close = true;
	}
	
	@Override
	public String toString() {
		return sessionToken + "@" + perm;
	}

}
