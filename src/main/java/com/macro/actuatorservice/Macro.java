package com.macro.actuatorservice;

import java.util.HashMap;
import java.util.Map;

public class Macro {
	private String macroName;
	private Map<String, String> results = new HashMap<String, String>();
	private String macroFilePath;
	
	public Macro(String macroName, Map<String, String> results, String macroFilePath) {
		super();
		this.macroName = macroName;
		this.results = results;
		this.macroFilePath = macroFilePath;
	}

	public String getMacroName() {
		return macroName;
	}

	public void setMacroName(String macroName) {
		this.macroName = macroName;
	}

	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}

	public String getMacroFilePath() {
		return macroFilePath;
	}

	public void setMacroFilePath(String macroFilePath) {
		this.macroFilePath = macroFilePath;
	}

	@Override
	public String toString() {
		return "Macro [macroName=" + macroName + ", results=" + results + ", macroFilePath=" + macroFilePath + "]";
	}
	
}
