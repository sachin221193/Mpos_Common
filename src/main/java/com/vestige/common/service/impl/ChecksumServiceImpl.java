package com.vestige.common.service.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.vestige.common.service.ChecksumService;
import com.vestige.common.service.dto.PaytmChecksumDTO;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.exceptions.ErrorConstants;

@Service
public class ChecksumServiceImpl implements ChecksumService {

	private final Logger log = LoggerFactory.getLogger(ChecksumServiceImpl.class);
	
	@Value(value = "${application.paytm.secretKey}")
	private String secretKey;

	private static final String CHECKSUM = "checksum";

	@Override
	public String generateChecksum(PaytmChecksumDTO model) {
		String checkSum = null;
		TreeMap<String, String> paramMap = new TreeMap<>();
		paramMap.put("MID", model.getMid());
		paramMap.put("ORDER_ID", model.getOrderId());
		paramMap.put("CUST_ID", model.getCustomerId());
		paramMap.put("INDUSTRY_TYPE_ID", model.getIndustryTypeId());
		paramMap.put("CHANNEL_ID", model.getChannelId());
		paramMap.put("TXN_AMOUNT", model.getTxnAmount());
		paramMap.put("WEBSITE", model.getWebsite());
		paramMap.put("EMAIL", model.getEmailId());
		paramMap.put("MOBILE_NO", model.getMobileNo());
		paramMap.put("CALLBACK_URL", model.getCallbackUrl());
		
		try {
			checkSum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(secretKey.trim(),
					paramMap);
			JSONObject response = new JSONObject();
			response.put(CHECKSUM, checkSum);
			return JSONObject.valueToString(response);
		} catch (Exception ex) {
			log.error("Paytm Payload {}", ex);
			throw new BadRequestAlertException(ex.getMessage(), "paytmChecksum", ErrorConstants.ERR_VALIDATION);
		}
	}

	@Override
	public String verifyChecksum(Map<String, String> params) {
		String paytmChecksum = null;
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		for (Entry<String, String> requestParamsEntry : params.entrySet()) {
			if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
				paytmChecksum = requestParamsEntry.getValue();
			} else {
				paytmParams.put(requestParamsEntry.getKey(), requestParamsEntry.getValue());
			}
		}
		try {
			return new String("{\"isValidCheckSum\":" + CheckSumServiceHelper.getCheckSumServiceHelper()
					.verifycheckSum(secretKey, paytmParams, paytmChecksum) + "}");
		} catch (Exception e) {
			log.error("Error while validating paytm checksum {}", e);
			throw new BadRequestAlertException(e.getMessage(), "paytmChecksum", ErrorConstants.ERR_VALIDATION);
		}
	}

}
