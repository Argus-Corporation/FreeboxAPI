package net.argus.api.freebox.api.call.voicemail;

import net.argus.cjson.value.CJSONObject;

public class VoiceMailEntry {
	
	private String phoneNumber;
	private boolean read;
	private String id;
	private int duration;
	private String countryCode;
	private int date;
	
	public VoiceMailEntry(CJSONObject obj) {
		phoneNumber = obj.getString("phone_number");
		read = obj.getBoolean("read");
		id = obj.getString("id");
		duration = obj.getInt("duration");
		countryCode = obj.getString("country_code");
		date = obj.getInt("date");
	}
	
	public String getPhoneNumber() {return phoneNumber;}
	public boolean isRead() {return read;}
	public String getId() {return id;}
	public int getDuration() {return duration;}
	public String getCountryCode() {return countryCode;}
	public int getDate() {return date;}
	
	@Override
	public String toString() {
		String str = "{";
		
		str += "phone_number: \"" + phoneNumber + "\", ";
		str += "read: " + read + ", ";
		str += "id: \"" + id + "\", ";
		str += "duration: " + duration + ", ";
		str += "country_code: \"" + countryCode + "\", ";
		str += "date: " + date + "";
		
		return str + "}";
	}

}
