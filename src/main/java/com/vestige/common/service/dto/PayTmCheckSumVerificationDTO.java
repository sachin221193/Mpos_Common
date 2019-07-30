package com.vestige.common.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayTmCheckSumVerificationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3108753084696940845L;

	@JsonProperty(value = "BANKNAME")
	private String bankName;

	@JsonProperty(value = "BANKTXNID")
	private String bankTxnId;

	@JsonProperty(value = "CHECKSUMHASH")
	private String checkSumHash;

	@JsonProperty(value = "CURRENCY")
	private String currency;

	@JsonProperty(value = "GATEWAYNAME")
	private String gatewayName;

	@JsonProperty(value = "MID")
	private String mid;

	@JsonProperty(value = "ORDERID")
	private String orderId;

	@JsonProperty(value = "PAYMENTMODE")
	private String paymentMode;

	@JsonProperty(value = "RESPCODE")
	private String respCode;

	@JsonProperty(value = "RESPMSG")
	private String respMsg;

	@JsonProperty(value = "STATUS")
	private String status;
	
	@JsonProperty(value = "TXNAMOUNT")
	private String txnAmount;

	@JsonProperty(value = "TXNDATE")
	private String txnDate;

	@JsonProperty(value = "TXNID")
	private String txnId;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankTxnId() {
		return bankTxnId;
	}

	public void setBankTxnId(String bankTxnId) {
		this.bankTxnId = bankTxnId;
	}

	public String getCheckSumHash() {
		return checkSumHash;
	}

	public void setCheckSumHash(String checkSumHash) {
		this.checkSumHash = checkSumHash;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

}
