package net.argus.api.freebox.net;

import net.argus.cjson.CJSONItem;
import net.argus.cjson.value.CJSONArray;
import net.argus.cjson.value.CJSONBoolean;
import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONString;
import net.argus.cjson.value.CJSONValue;

public class FbxPackageBuilder {
	
	public static FbxPackage getPack(FbxPackEntry ... entrys) {
		return getPackArray(entrys);
	}
	
	public static FbxPackage getPackArray(FbxPackEntry[] entrys) {
		FbxPackage pack = new FbxPackage();
		
		for(FbxPackEntry entry : entrys) {
			CJSONValue val = null;
			if(entry.getValue() instanceof String)
				val = new CJSONString((String) entry.getValue());
			if(entry.getValue() instanceof Integer)
				val = new CJSONInteger((int) entry.getValue());
			if(entry.getValue() instanceof Boolean)
				val = new CJSONBoolean((boolean) entry.getValue());
			if(entry.getValue() instanceof CJSONValue[])
				val = new CJSONArray((CJSONValue[]) entry.getValue());
			
			pack.addItem(new CJSONItem(entry.getName(), val));
		}
		
		return pack;
	}

}
