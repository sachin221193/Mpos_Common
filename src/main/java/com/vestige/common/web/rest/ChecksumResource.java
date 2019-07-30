package com.vestige.common.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.vestige.common.service.ChecksumService;
import com.vestige.common.service.dto.PaytmChecksumDTO;
import com.vestige.common.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Checksum.
 */
@RestController
@RequestMapping("/api/" + "${application.api.version}")
public class ChecksumResource {

	private final Logger log = LoggerFactory.getLogger(ChecksumResource.class);

	private static final String ENTITY_NAME = "Checksum";

	private final ChecksumService checksumService;

	@Value(value = "${application.api.version}")
	private String version;

	public ChecksumResource(ChecksumService checksumService) {
		this.checksumService = checksumService;
	}

	/**
	 * POST /paytm/checksum : Create a new checksum.
	 *
	 * @param paytmChecksumModel the paytmChecksumModel to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         paytmChecksumModel, or with status 400 (Bad Request)
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/paytm/checksum")
	@Timed
	public ResponseEntity<String> createChecksum(@Valid @RequestBody PaytmChecksumDTO model)
			throws URISyntaxException {
		log.debug("REST request to paytmChecksumModel  : {}", model);

		String result = checksumService.generateChecksum(model);
		return ResponseEntity.created(new URI("/api/" + version + "/paytm/checksum"))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result)).body(result);
	}
	
	/**
	 * POST /paytm/checksum : Create a new checksum.
	 *
	 * @param paytmChecksumModel the paytmChecksumModel to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         paytmChecksumModel, or with status 400 (Bad Request)
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/paytm/checksum/verification")
	@Timed
	public ResponseEntity<String> checkSumVerification(@Valid @RequestBody Map<String, String> params){
		log.debug("REST request to paytmChecksumRequest : {}", params);

		String result = checksumService.verifyChecksum(params);
		return ResponseEntity.ok(result);
	}
	
}
