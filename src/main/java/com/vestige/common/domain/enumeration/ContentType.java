package com.vestige.common.domain.enumeration;

/**
 * The ContentType enumeration.
 */
public enum ContentType {
	ABOUT_US("AboutUs"),
	DELIVERY_CHARGES("DeliveryCharges");

	private final String value;

	private ContentType(String value) {
	 this.value = value;
	}

	public String getValue() {
	  return value;
	}
}