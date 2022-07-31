package net.argus.api.freebox;

import java.util.HashMap;
import java.util.Map;

import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.CJSONItem;
import net.argus.cjson.value.CJSONObject;

public class SessionPermission extends Permission<SessionPermissions, Boolean> {

	public SessionPermission(Map<SessionPermissions, Boolean> map) {
		super(map);
	}
	
	public static SessionPermission getPermissions(FbxRequestReturn ret) {
		if(!ret.isSuccess())
			return null;
		
		CJSONObject objPerm = ret.getResult().getObject("permissions");
		Map<SessionPermissions, Boolean> map = new HashMap<SessionPermissions, Boolean>();
		for(CJSONItem item : objPerm.getValue()) {
			SessionPermissions perm = SessionPermissions.getPermission(item.getName());
			if(perm == null)
				continue;
			
			map.put(perm, Boolean.valueOf(item.getValue().toString()));
		}
		
		return new SessionPermission(map);
	}

}
