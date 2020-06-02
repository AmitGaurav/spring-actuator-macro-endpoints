package com.macro.actuatorservice;

public class Event {
	
	private String paramName;
	private String dataType; // TODO: Enum candidate
	private Object paramValue;

	public Event() {
	}
	
	@Override
	public String toString() {
		return "Event [paramName=" + paramName + ", dataType=" + dataType + ", paramValue=" + paramValue + "]";
	}
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Object getParamValue() {
		return paramValue;
	}
	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
	
	public Event(String paramName, Object paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public Event(String paramName, String dataType, Object paramValue) {
		super();
		this.paramName = paramName;
		this.dataType = dataType;
		this.paramValue = paramValue;
	}
	
}
