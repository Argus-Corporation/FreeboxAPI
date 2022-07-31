package net.argus.api.freebox.net;

import net.argus.cjson.CJSON;
import net.argus.cjson.CJSONParser;

public class FbxRequestReturn {
	
	private CJSON respons;
	private boolean success;
	private String errorCode;
	private String msg;
	
	public FbxRequestReturn(String respons) {
		this.respons = CJSONParser.getCJSON(respons);
		if(this.respons.getValue("success") != null) {
			this.success = this.respons.getBoolean("success");
			if(!this.success) {
				this.errorCode = this.respons.getString("error_code");
				this.msg = this.respons.getString("msg");
			}
		}
	}
	
	public CJSON getRespons() {
		return respons;
	}
	
	public CJSON getResult() {
		if(!success)
			return null;
		return CJSONParser.getCJSON(respons.getObject("result").toString());
	}
	
	public boolean isSuccess() {return success;}
	public String getErrorCode() {return errorCode;}
	public String getMessage() {return msg;}
	
	@Override
	public String toString() {
		return respons.toString();
	}

}
