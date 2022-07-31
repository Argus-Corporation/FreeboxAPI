package net.argus.api.freebox;

import java.util.HashMap;
import java.util.Map;

import net.argus.api.freebox.net.FbxRequestReturn;
import net.argus.cjson.CJSONItem;
import net.argus.cjson.value.CJSONObject;

public class Permission {
	
	private Map<Permissions, Boolean> mapPerm = new HashMap<Permissions, Boolean>();
	
	public Permission(Map<Permissions, Boolean> map) {
		this.mapPerm = map;
	}
	
	public static Permission getPermissions(FbxRequestReturn ret) {
		if(!ret.isSuccess())
			return null;
		
		CJSONObject objPerm = ret.getResult().getObject("permissions");
		Map<Permissions, Boolean> map = new HashMap<Permissions, Boolean>();
		for(CJSONItem item : objPerm.getValue()) {
			Permissions perm = Permissions.getPermission(item.getName());
			if(perm == null)
				continue;
			
			map.put(perm, Boolean.valueOf(item.getValue().toString()));
		}
		
		return new Permission(map);
	}
	
	@Override
	public String toString() {
		return mapPerm.toString();
	}
	
	public boolean test(Permissions perm) {
		return mapPerm.get(perm);
	}

}
