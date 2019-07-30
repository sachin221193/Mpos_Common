package com.vestige.common.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.vestige.common.service.PincodeService;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.common.web.rest.vm.DistributorLocationVm;
import com.vestige.common.web.rest.vm.LocationData;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.model.dto.PincodeDTO;

/**
 * REST controller for managing Pincode.
 */
@RestController
@RequestMapping("/api/"+"${application.api.version}")
public class PincodeResource {

	private final Logger log = LoggerFactory.getLogger(PincodeResource.class);

	private static final String ENTITY_NAME = "pincode";

	private final PincodeService pincodeService;
	
	@Value(value="${application.api.version}")
	private String version;

	public PincodeResource(PincodeService pincodeService) {
		this.pincodeService = pincodeService;
	}

	/**
	 * POST /pincodes : Create a new pincode.
	 *
	 * @param pincodeDTO the pincodeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         pincodeDTO, or with status 400 (Bad Request) if the pincode has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/pincodes")
	@Timed
	public ResponseEntity<PincodeDTO> createPincode(@RequestBody PincodeDTO pincodeDTO) throws URISyntaxException {
		log.debug("REST request to save Pincode : {}", pincodeDTO);
		if (pincodeDTO.getId() != null) {
			throw new BadRequestAlertException("A new pincode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		PincodeDTO result = pincodeService.save(pincodeDTO);
		return ResponseEntity.created(new URI("/api/pincodes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId())).body(result);
	}

	/**
	 * PUT /pincodes : Updates an existing pincode.
	 *
	 * @param pincodeDTO the pincodeDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         pincodeDTO, or with status 400 (Bad Request) if the pincodeDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the pincodeDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/pincodes")
	@Timed
	public ResponseEntity<PincodeDTO> updatePincode(@RequestBody PincodeDTO pincodeDTO) throws URISyntaxException {
		log.debug("REST request to update Pincode : {}", pincodeDTO);
		if (pincodeDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		PincodeDTO result = pincodeService.save(pincodeDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pincodeDTO.getId())).body(result);
	}

	/**
	 * GET /pincodes : get all the pincodes.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of pincodes in
	 *         body
	 */
	@GetMapping("/pincodes")
	@Timed
	public ResponseEntity<List<PincodeDTO>> getAllPincodes(Pageable pageable) {
		log.debug("REST request to get a page of Pincodes");
		Page<PincodeDTO> page = pincodeService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pincodes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * DELETE /pincodes/:id : delete the "id" pincode.
	 *
	 * @param id the id of the pincodeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/pincodes/{id}")
	@Timed
	public ResponseEntity<Void> deletePincode(@PathVariable String id) {
		log.debug("REST request to delete Pincode : {}", id);
		pincodeService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}

	/**
	 * GET /pincodes/{pincode} : get the "pincode" pincode.
	 *
	 * @param pincode of the pincodeDTO to retrieve
	 * @return pincodeDTO
	 */
	@GetMapping("/pincodes/{pincode}")
	@Timed
	public PincodeDTO getPincodeByPincodeNumber(@PathVariable String pincode) {
		log.debug("REST request to get Pincode : {}", pincode);
		return pincodeService.findByPinCode(pincode);
	}
	
	/**
	 * GET /pincodes : get all the pincodes on basis of CountryId, StateId, CityId.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of pincodes in
	 *         body
	 */
	@GetMapping("/pincodes/country/{countryid}/state/{stateid}/city/{cityid}")
	@Timed
	public ResponseEntity<List<PincodeDTO>> getAllPincodes(Pageable pageable,@PathVariable Integer countryid, @PathVariable Integer stateid, 
			@PathVariable Integer cityid) {
		log.debug("REST request to get a page of Pincodes on basis of CountryId, StateId, CityId");
		Page<PincodeDTO> page = pincodeService.findPincodeByCountryStateCity(pageable,countryid,stateid,cityid);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/"+version+"/pincodes/country/"+countryid+"/state/"+stateid+"/city/"+cityid);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	 * GET /pincodes/location/{pincode} : get the "pincode" pincode.
	 *
	 * @param pincode of the pincodeDTO to retrieve
	 * @return pincodeDTO
	 */
	@GetMapping("/pincodes/location/{pincode}")
	@Timed
	public LocationData getLocationDataByPincode(@PathVariable String pincode) {
		log.debug("REST request to get Pincode : {}", pincode);
		return pincodeService.findLocationDataByPincode(pincode);
	}
	
	/**
	 * GET /pincodes : get all the pincodes on basis of CountryId, StateId, CityId, Pincode.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of pincodes in
	 *         body
	 */
	@GetMapping("/pincodes/country/{countryId}/state/{stateId}/city/{cityId}/pincode/{pincode}")
	public ResponseEntity<DistributorLocationVm> getDistributorLocation(@PathVariable Integer countryId, @PathVariable Integer stateId, 
			@PathVariable Integer cityId, @PathVariable String pincode) {
		log.debug("REST request to get a page of Pincodes on basis of CountryId, StateId, CityId, Pincode");
		return ResponseEntity.ok(pincodeService.findDistributorLocation(countryId, stateId, cityId, pincode));
	}
}
