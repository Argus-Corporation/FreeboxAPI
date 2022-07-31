package net.argus.api.freebox;

public class FreeboxSession {
	
	private String sessionToken;
	private SessionPermission perm;
	
	private boolean close;
	
	public FreeboxSession(String sesstionToken, SessionPermission perm) {
		this.sessionToken = sesstionToken;
		this.perm = perm;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public SessionPermission getPerm() {
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
