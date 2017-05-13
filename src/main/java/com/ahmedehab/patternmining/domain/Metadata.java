package com.ahmedehab.patternmining.domain;

public class Metadata {
	private String success;
	
	private long runtime;

	private double memory;
	
	public Metadata(String success, long runtime) {
		this.success = success;
		this.runtime = runtime;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public long getRuntime() {
		return runtime;
	}

	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}

	public double getMemory() {
		return memory;
	}

	public void setMemory(double memory) {
		this.memory = memory;
	}
	
}
