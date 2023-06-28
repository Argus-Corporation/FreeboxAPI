package net.argus.api.freebox.api.call.voicemail;

import java.util.ArrayList;
import java.util.List;

import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.Array;
import net.argus.cjson.value.CJSONObject;

public class VoiceMails {
	
	private List<VoiceMailEntry> mailEntries = new ArrayList<VoiceMailEntry>();
	
	public VoiceMails(FbxRequestReturn ret) {
		Array ar = ret.getResultArray();
		for(int i = 0; i < ar.length(); i++)
			mailEntries.add(new VoiceMailEntry((CJSONObject) ar.get(i)));
	}
	
	public void update(VoiceMailEntry entry) {
		int index = index(entry.getId());
		
		if(index == -1)
			return;
		
		mailEntries.set(index, entry);
	}
	
	public VoiceMailEntry get(String id) {
		for(VoiceMailEntry entry : mailEntries)
			if(entry.getId().equals(id))
				return entry;
		return null;
	}
	
	public int index(String id) {
		for(int i = 0; i < mailEntries.size(); i++)
			if(mailEntries.get(i).getId().equals(id))
				return i;
		return -1;
	}
	
	public List<VoiceMailEntry> getVoiceMailEntries() {
		return mailEntries;
	}
	
	@Override
	public String toString() {
		return "voice_mails@" + mailEntries;
	}

}
