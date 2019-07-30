package com.vestige.common.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.vestige.common.service.ZoneService;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.model.dto.ZoneDTO;

/**
 * REST controller for managing Zone.
 */
@RestController
@RequestMapping("/api/"+"${application.api.version}")
public class ZoneResource {

	private final Logger log = LoggerFactory.getLogger(ZoneResource.class);

	private static final String ENTITY_NAME = "zone";

	private final ZoneService zoneService;

	public ZoneResource(ZoneService zoneService) {
		this.zoneService = zoneService;
	}

	/**
	 * POST /zones : Create a new zone.
	 *
	 * @param zoneDTO the zoneDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         zoneDTO, or with status 400 (Bad Request) if the zone has already an
	 *         ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/zones")
	@Timed
	public ResponseEntity<ZoneDTO> createZone(@RequestBody ZoneDTO zoneDTO) throws URISyntaxException {
		log.debug("REST request to save Zone : {}", zoneDTO);
		if (zoneDTO.getId() != null) {
			throw new BadRequestAlertException("A new zone cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ZoneDTO result = zoneService.save(zoneDTO);
		return ResponseEntity.created(new URI("/api/zones/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId())).body(result);
	}

	/**
	 * PUT /zones : Updates an existing zone.
	 *
	 * @param zoneDTO the zoneDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         zoneDTO, or with status 400 (Bad Request) if the zoneDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the zoneDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/zones")
	@Timed
	public ResponseEntity<ZoneDTO> updateZone(@RequestBody ZoneDTO zoneDTO) throws URISyntaxException {
		log.debug("REST request to update Zone : {}", zoneDTO);
		if (zoneDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ZoneDTO result = zoneService.save(zoneDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, zoneDTO.getId()))
				.body(result);
	}

	/**
	 * GET /zones : get all the zones.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of zones in body
	 */
	@GetMapping("/zones")
	@Timed
	public ResponseEntity<List<ZoneDTO>> getAllZones(Pageable pageable) {
		log.debug("REST request to get a page of Zones");
		Page<ZoneDTO> page = zoneService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/zones");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * DELETE /zones/:id : delete the "id" zone.
	 *
	 * @param id the id of the zoneDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/zones/{id}")
	@Timed
	public ResponseEntity<Void> deleteZone(@PathVariable String id) {
		log.debug("REST request to delete Zone : {}", id);
		zoneService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}

	//TODO:set response
	/**
	 * GET /zones/{id} 
	 *
	 * @param id to retrieve
	 * @return zoneDto
	 */
	@GetMapping("/zones/{id}")
	@Timed
	public ZoneDTO getZoneByZoneId(@PathVariable Integer id) {
		log.debug("REST request to get a page of Zones");
		return zoneService.findByZoneId(id);
	}
}
