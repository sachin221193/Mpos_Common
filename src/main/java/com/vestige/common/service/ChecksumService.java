package com.vestige.common.service;

import java.util.Map;

import com.vestige.common.service.dto.PaytmChecksumDTO;

/**
 * Service Interface for managing Checksum.
 */
public interface ChecksumService {

	/**
	 *
	 * @param PaytmChecksumDTO the checksum
	 * @return the string
	 */
	String generateChecksum(PaytmChecksumDTO model);

	String verifyChecksum(Map<String, String> params);

}
