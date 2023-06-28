package net.argus.api.freebox.net;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import net.argus.cjson.Array;
import net.argus.cjson.CJSON;
import net.argus.cjson.CJSONParser;
import net.argus.cjson.value.CJSONArray;
import net.argus.exception.CJSONException;
import net.argus.util.debug.Debug;
import net.argus.util.debug.Info;

public class FbxRequestReturn {
	
	private CJSON respons;
	private boolean success;
	private String errorCode;
	private String msg;
	
	private String fileName;
	private byte[] buffer;
	
	private int httpCode;
	
	public FbxRequestReturn(String respons, int httpCode, Map<String, List<String>> header) {
		try {
			this.respons = CJSONParser.getCJSON(respons);
			this.httpCode = httpCode;
			
			if(this.respons.getValue("success") != null) {
				this.success = this.respons.getBoolean("success");
				if(!this.success) {
					this.errorCode = this.respons.getString("error_code");
					this.msg = this.respons.getString("msg");
					Debug.log("Http code " + httpCode + " with message \"" + msg + "\" !", Info.ERROR);
				}
			}
		}catch(CJSONException e) {
			if(httpCode != HttpURLConnection.HTTP_OK)
				return;
			success = true;
			
			fileName = header.get("Content-Disposition").get(0);
			fileName = fileName.substring(fileName.indexOf('=')+2, fileName.length() - 1);
			
			buffer = respons.getBytes();
		}
	}
	
	public CJSON getRespons() {
		return respons;
	}
	
	public CJSON getResult() {
		if(!success)
			return null;
		if(respons.getValue("result") instanceof CJSONArray)
			return null;
		if(respons.getValue("result") == null)
			return null;
		return CJSONParser.getCJSON(respons.getObject("result").toString());
	}
	
	public Array getResultArray() {
		if(!success)
			return null;
		if(!(respons.getValue("result") instanceof CJSONArray))
			return null;
		if(respons.getValue("result") == null)
			return null;
		return respons.getArray("result");
	}
	
	public boolean isSuccess() {return success;}
	public String getErrorCode() {return errorCode;}
	public String getMessage() {return msg;}
	public int getHttpCode() {return httpCode;}
	
	public String getFileName() {
		return fileName;
	}
	
	public byte[] getBuffer() {
		return buffer;
	}
	
	@Override
	public String toString() {
		if(respons != null)
			return respons.toString();
		else
			return fileName;
	}

}
