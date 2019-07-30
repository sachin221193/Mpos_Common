package com.vestige.common.config;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class TokenProvider {

	//TODO: CHECK with Vijay, whether to check joken is valid or not?
	public static String getJwtTokenFieldValue(String key) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if(authentication != null) {
			Object details = authentication.getDetails();
			if(details instanceof OAuth2AuthenticationDetails) {
				Object decodedDetails = ((OAuth2AuthenticationDetails) details).getDecodedDetails();
				if(decodedDetails != null & decodedDetails instanceof Map) {
					return (String)((Map)decodedDetails).get(key);
				}
			}
		}
		return "";
	}
	
	public static String getJwtTokenWrapperToken() {
		return getJwtTokenFieldValue("ut");
	}
	
	public static String getUserId() {
		return getJwtTokenFieldValue("user_name");
	}
	
	public static String getUserName() {
		return getJwtTokenFieldValue("name");
	}
}