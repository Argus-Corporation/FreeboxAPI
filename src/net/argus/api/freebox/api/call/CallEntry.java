package net.argus.api.freebox.api.call;

import net.argus.cjson.value.CJSONObject;

public class CallEntry {
	
	private String number;
	private TypeCall type;
	private int id;
	private int duration;
	private int datetime;
	private int contactId;
	private int lineId;
	private String name;
	private boolean new0;
	
	public CallEntry(CJSONObject logReturnObject) {
		number = logReturnObject.getString("number");
		type = TypeCall.getType(logReturnObject.getString("type"));
		id = logReturnObject.getInt("id");
		duration = logReturnObject.getInt("duration");
		datetime = logReturnObject.getInt("datetime");
		contactId = logReturnObject.getInt("contact_id");
		lineId = logReturnObject.getInt("line_id");
		name = logReturnObject.getString("name");
		new0 = logReturnObject.getBoolean("new");
	}
	
	
	public String getNumber() {return number;}

	public TypeCall getType() {return type;}

	public int getId() {return id;}

	public int getDuration() {return duration;}

	public int getDatetime() {return datetime;}

	public int getContactId() {return contactId;}

	public int getLineId() {return lineId;}

	public String getName() {return name;}

	public boolean isNew() {return new0;}


	@Override
	public String toString() {
		String str = "{";
		
		str += "number: \"" + number + "\", ";
		str += "type: \"" + type + "\", ";
		str += "id: " + id + ", ";
		str += "duration: " + duration + ", ";
		str += "datetime: " + datetime + ", ";
		str += "contact_id: " + contactId + ", ";
		str += "line_id: " + lineId + ", ";
		str += "name: \"" + name + "\", ";
		str += "new: " + new0 + "";
		
		return str + "}";
	}

}
