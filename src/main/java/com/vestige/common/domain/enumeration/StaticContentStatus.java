package com.vestige.common.domain.enumeration;

/**
 * The ContentType enumeration.
 */
public enum StaticContentStatus {
	PENDING(0,"false"),
	ACCEPTED(1,"true"),
	REJECTED(2,"deleted");

	private final Integer key;
	private final String value;

	private StaticContentStatus(Integer key,String value) {
		this.key = key;
		this.value = value;
	}

	public String getValue() {
	  return value;
	}
	
	public Integer getKey() {
		return key;
	}
}