package com.vestige.common.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";
    
    public static final long FILE_MIN_SIZE = 10240; // Set file size to 10KB.
	public static final long FILE_MAX_SIZE = 157286400; // Set file size to 150MB.
    
    private Constants() {
    }
}
