package com.tennissetapp.json;

//@JsonSerialize
//@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class JsonEntry {
	
//	@JsonProperty
	public String label;
	public String value;
	
	public JsonEntry(){}
	
	public JsonEntry(String label,String value){
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

