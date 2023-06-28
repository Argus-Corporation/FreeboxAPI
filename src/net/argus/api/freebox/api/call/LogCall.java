package net.argus.api.freebox.api.call;

import java.util.ArrayList;
import java.util.List;

import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.Array;
import net.argus.cjson.value.CJSONObject;

public class LogCall {
	
	private List<CallEntry> callEntries = new ArrayList<CallEntry>();
	
	public LogCall(FbxRequestReturn ret) {
		Array ar = ret.getResultArray();
		for(int i = 0; i < ar.length(); i++)
			callEntries.add(new CallEntry((CJSONObject) ar.get(i)));
	}
	
	public void update(CallEntry entry) {
		int index = index(entry.getId());
		
		if(index == -1)
			return;
		
		callEntries.set(index, entry);
	}
	
	public CallEntry get(int id) {
		for(CallEntry entry : callEntries)
			if(entry.getId() == id)
				return entry;
		return null;
	}
	
	public int index(int id) {
		for(int i = 0; i < callEntries.size(); i++)
			if(callEntries.get(i).getId() == id)
				return i;
		return -1;
	}
	
	public List<CallEntry> getCallEntries() {
		return callEntries;
	}
	
	@Override
	public String toString() {
		return "log_call@" + callEntries;
	}

}
