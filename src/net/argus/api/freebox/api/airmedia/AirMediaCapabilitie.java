package net.argus.api.freebox.api.airmedia;

import java.util.HashMap;
import java.util.Map;

import net.argus.api.freebox.Permission;
import net.argus.cjson.CJSONItem;
import net.argus.cjson.value.CJSONObject;

public class AirMediaCapabilitie extends Permission<AirMediaCapabilities, Boolean> {
	
	public AirMediaCapabilitie(Map<AirMediaCapabilities, Boolean> map) {
		super(map);
	}

	public static AirMediaCapabilitie getCapabilitie(CJSONObject obj) {
		if(obj == null)
			return null;
		
		Map<AirMediaCapabilities, Boolean> map = new HashMap<AirMediaCapabilities, Boolean>();
		for(CJSONItem item : obj.getValue()) {
			AirMediaCapabilities perm = AirMediaCapabilities.getCapabilitie(item.getName());
			if(perm == null)
				continue;
			
			map.put(perm, Boolean.valueOf(item.getValue().toString()));
		}
		
		return new AirMediaCapabilitie(map);
	}

}
