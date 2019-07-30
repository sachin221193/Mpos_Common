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
import com.vestige.common.service.CityService;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.common.web.rest.vm.CityVM;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.model.dto.CityDTO;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api/"+"${application.api.version}")
public class CityResource {

    private final Logger log = LoggerFactory.getLogger(CityResource.class);

    private static final String ENTITY_NAME = "city";

    private final CityService cityService;
    
    @Value(value="${application.api.version}")
	private String version;

    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * POST  /cities : Create a new city.
     *
     * @param cityDTO the cityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cityDTO, or with status 400 (Bad Request) if the city has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cities")
    @Timed
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) throws URISyntaxException {
        log.debug("REST request to save City : {}", cityDTO);
        if (cityDTO.getId() != null) {
            throw new BadRequestAlertException("A new city cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.created(new URI("/api/cities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /cities : Updates an existing city.
     *
     * @param cityDTO the cityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cityDTO,
     * or with status 400 (Bad Request) if the cityDTO is not valid,
     * or with status 500 (Internal Server Error) if the cityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cities")
    @Timed
    public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) throws URISyntaxException {
        log.debug("REST request to update City : {}", cityDTO);
        if (cityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cityDTO.getId()))
            .body(result);
    }

    /**
     * GET  /cities : get all the cities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cities in body
     */
    @GetMapping("/cities")
    @Timed
    public ResponseEntity<List<CityDTO>> getAllCities(Pageable pageable) {
        log.debug("REST request to get a page of Cities");
        Page<CityDTO> page = cityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

   /**
     * DELETE  /cities/:id : delete the "id" city.
     *
     * @param id the id of the cityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cities/{id}")
    @Timed
    public ResponseEntity<Void> deleteCity(@PathVariable String id) {
        log.debug("REST request to delete City : {}", id);
        cityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

     /**
     * GET  /cities/{id}
     *
     * @param id the cityId of the cityDTO to retrieve
     * @return cityDTO
     */
    @GetMapping("/cities/{id}")
    @Timed
    public CityDTO getCityByCityId(@PathVariable Integer id) {
        log.debug("REST request to get City : {}", id);
        return cityService.findByCityId(id);
    }
    
    /**
     * GET  /cities : get all the cities on basis of StateId.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cities in body
     */
    @GetMapping("/cities/state/{id}")
    @Timed
    public ResponseEntity<List<CityDTO>> getAllCitiesByStateId(Pageable pageable, @PathVariable Integer id) {
        log.debug("REST request to get a page of Cities on basis of StateId");
        Page<CityDTO> page = cityService.findAllCitiesByStateId(pageable,id);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/"+version+"/cities/state/"+id);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
	/****
	 * @description API for finding city on the basis of country, state and city name
	 * @param countryName, stateName, cityName
	 * @return city
	 */
    @GetMapping("/cities/{countryName}/{stateName}/{cityName}")
    public CityVM getCityByName(@PathVariable String countryName, 
    		@PathVariable String stateName, @PathVariable String cityName  ) {
    	log.debug("Rest Request to get City on basis of name");
    	return cityService.findByName(countryName, stateName, cityName);
				   	
    }

}
