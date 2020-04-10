package com.marco.dishesservice.config.security;

public class DomainObjectReference {

	private String type;
	private String id;

	public DomainObjectReference(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}