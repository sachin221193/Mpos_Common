package com.vestige.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Common.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private final Api api = new Api();

	private final PayTm payTm = new PayTm();

	private final FileUpload fileUpload = new FileUpload();

	public static class Api {

		private String version;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

	}

	public Api getApi() {
		return api;
	}

	public static class PayTm {

		private String secretKey;

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

	}

	public PayTm getPayTm() {
		return payTm;
	}

	public static class FileUpload {

		private String secretId;

		private String secretKey;

		private String regionName;

		private String bucketName;

		private String tempPath;

		public String getSecretId() {
			return secretId;
		}

		public void setSecretId(String secretId) {
			this.secretId = secretId;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

		public String getTempPath() {
			return tempPath;
		}

		public void setTempPath(String tempPath) {
			this.tempPath = tempPath;
		}

	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}
}
