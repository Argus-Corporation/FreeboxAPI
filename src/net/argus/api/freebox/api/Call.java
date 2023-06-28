package net.argus.api.freebox.api;

import java.io.IOException;

import net.argus.api.freebox.FreeboxProperties;
import net.argus.api.freebox.FreeboxSession;
import net.argus.api.freebox.api.call.CallEntry;
import net.argus.api.freebox.api.call.LogCall;
import net.argus.api.freebox.api.call.voicemail.VoiceMailEntry;
import net.argus.api.freebox.api.call.voicemail.VoiceMails;
import net.argus.api.freebox.net.FbxIO;
import net.argus.api.freebox.net.FbxPackPref;
import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.util.debug.Debug;

public class Call extends FbxAPI {
	
	private LogCall logCall;
	private VoiceMails voiceMails;

	Call(FreeboxProperties properties, FreeboxSession session) throws IOException {
		super(properties, session);
	}

	@Override
	protected void init() throws IOException {
		initLog();
		initVoiceMail();
	}
	
	protected void initLog() throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/call/log/", session, properties);

		if(ret == null || (ret.getResult() == null && ret.getResultArray() == null) || !ret.isSuccess())
			return;
		
		logCall = new LogCall(ret);
	}
	
	protected void initVoiceMail() throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/call/voicemail/", session, properties);

		if(ret == null || ret.getResult() == null || !ret.isSuccess())
			return;
		
		voiceMails = new VoiceMails(ret);
	}
	
	public void deleteAll() throws IOException {
		FbxRequestReturn ret = FbxIO.sendPOST("/call/log/delete_all", null, session, properties);
		if(ret == null || !ret.isSuccess())
			return;
		
		initLog();
		Debug.log("Call: all calls was deleted successfully");
	}
	
	public void markAllRead() throws IOException {
		FbxRequestReturn ret = FbxIO.sendPOST("/call/log/mark_all_as_read", null, session, properties);
		if(ret == null || !ret.isSuccess())
			return;

		initLog();
		Debug.log("Call: all calls have been marked as read");
	}
	
	public void delete(int id) throws IOException {
		FbxRequestReturn ret = FbxIO.sendDELETE("/call/log/" + id, session, properties);
		if(ret == null || !ret.isSuccess())
			return;
		
		initLog();
		Debug.log("Call: call " + id + " was successfully deleted");
	}
	
	public void update(int id) throws IOException {
		if(logCall == null)
			return;
		
		CallEntry entry = logCall.get(id);
		if(entry == null)
			return;
		
		update(id, !entry.isNew());
	}
	
	public void update(int id, boolean new0) throws IOException {
		FbxRequestReturn ret = FbxIO.sendPUT("/call/log/" + id, FbxPackPref.getUpdateCall(new0), session, properties);
		if(ret == null || !ret.isSuccess())
			return;
		
		logCall.update(new CallEntry(ret.getResult().getMainObject()));
		Debug.log("Call: call " + id + " was successfully updated");
	}
	
	/**
	 * get your phone number
	 * @throws IOException 
	 */
	public String getAccount() throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/call/account/", session, properties);
		if(ret == null || !ret.isSuccess())
			return null;
		
		return ret.getResult().getString("phone_number");
	}
	
	public LogCall getLogCall() {
		return logCall;
	}
	
	
	
	//**--------VoiceMail--------**\\
	public void deleteVoiceMail(String id) throws IOException {
		 FbxRequestReturn ret = FbxIO.sendDELETE("/call/voicemail/" + id, session, properties);
		if(ret == null || !ret.isSuccess())
			return;
			
		initVoiceMail();
		Debug.log("Call: voicemail " + id + " was successfully deleted");
	 }
	
	 
	public void updateVoiceMail(String id) throws IOException {
		if(logCall == null)
			return;
		
		VoiceMailEntry entry = voiceMails.get(id);
		if(entry == null)
			return;
			
		updateVoiceMail(id, !entry.isRead());
	}
		
	public void updateVoiceMail(String id, boolean read) throws IOException {
		FbxRequestReturn ret = FbxIO.sendPUT("/call/voicemail/" + id, FbxPackPref.getUpdateVoiceMail(read), session, properties);
		if(ret == null || !ret.isSuccess())
			return;
		
		logCall.update(new CallEntry(ret.getResult().getMainObject()));
		Debug.log("Call: voicemail " + id + " was successfully updated");
	}
	
	public FbxRequestReturn retrieveVoiceMail(String id) throws IOException {
		FbxRequestReturn ret = FbxIO.sendGET("/call/voicemail/" + id + "/audio_file", session, properties);
		if(ret == null || !ret.isSuccess())
			return null;
		
		return ret;
	}
	
	
	public VoiceMails getVoiceMails() {
		return voiceMails;
	}
		

	@Override
	public boolean validAccess(FreeboxSession session) {
		return true;
	}

}
