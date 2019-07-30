package com.vestige.common.service.dto;

import java.io.Serializable;

public class PaytmChecksumDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4208113176177748874L;
	private String mid;
	private String orderId;
	private String customerId;
	private String industryTypeId;
	private String channelId;
	private String txnAmount;
	private String website;
	private String emailId;
	private String mobileNo;
	private String callbackUrl;
	private String mode;

	public String getMid() {
		return mid.trim();
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getOrderId() {
		return orderId.trim();
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId.trim();
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIndustryTypeId() {
		return industryTypeId.trim();
	}

	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getChannelId() {
		return channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTxnAmount() {
		return txnAmount.trim();
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getWebsite() {
		return website.trim();
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmailId() {
		return emailId.trim();
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo.trim();
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCallbackUrl() {
		return callbackUrl.trim();
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getMode() {
		return mode.trim();
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
