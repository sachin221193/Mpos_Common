package com.vestige.common.domain.enumeration;

/**
 * The QueryType enumeration.
 */
public enum QueryType {
	NETWORKING, PRODUCTS, ORDERS, OTHERS;
    
    public static QueryType getQueryType(String value) {
    	QueryType queryType = null; 
		if(value.equals("NETWORKING")) {
			queryType = NETWORKING;
		}
		if(value.equals("PRODUCTS")) {
			queryType = PRODUCTS;
		}
		if(value.equals("ORDERS")) {
			queryType = ORDERS;
		}
		if(value.equals("OTHERS")) {
			queryType = OTHERS;
		}
		return queryType;
	}
}
