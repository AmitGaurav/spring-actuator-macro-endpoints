package com.macro.actuatorservice;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
	
	private String eventName;
	private List<Event> param = new ArrayList<Event>();
	
	public Parameters(){
	}
	
	public Parameters(String eventName, List<Event> param) {
		super();
		this.eventName = eventName;
		this.param = param;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public List<Event> getParam() {
		return param;
	}
	public void setParam(List<Event> param) {
		this.param = param;
	}
	
	@Override
	public String toString() {
		return "Parameters [eventName=" + eventName + ", param=" + param + "]";
	}
	
	
}
