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
import com.vestige.common.service.CountryService;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.model.dto.CountryDTO;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api/"+"${application.api.version}")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "country";

    private final CountryService countryService;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * POST  /countries : Create a new country.
     *
     * @param countryDTO the countryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new countryDTO, or with status 400 (Bad Request) if the country has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/countries")
    @Timed
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to save Country : {}", countryDTO);
        if (countryDTO.getId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /countries : Updates an existing country.
     *
     * @param countryDTO the countryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated countryDTO,
     * or with status 400 (Bad Request) if the countryDTO is not valid,
     * or with status 500 (Internal Server Error) if the countryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/countries")
    @Timed
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to update Country : {}", countryDTO);
        if (countryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryDTO.getId()))
            .body(result);
    }

    /**
     * GET  /countries : get all the countries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     */
    @GetMapping("/countries")
    @Timed
    public ResponseEntity<List<CountryDTO>> getAllCountries(Pageable pageable) {
        log.debug("REST request to get a page of Countries");
        Page<CountryDTO> page = countryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/countries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

  
    /**
     * DELETE  /countries/:id : delete the "id" country.
     *
     * @param id the id of the countryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/countries/{id}")
    @Timed
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * GET  /countries/{id} 
     *
     * @param id the countryId of the countryDTO to retrieve
     * @return the countryDTO
     */
    @GetMapping("/countries/{id}")
    @Timed
    public CountryDTO getCountryByCountryId(@PathVariable Integer id) {
        log.debug("REST request to get Country : {}", id);
        return countryService.findByCountryId(id);
    }
}
