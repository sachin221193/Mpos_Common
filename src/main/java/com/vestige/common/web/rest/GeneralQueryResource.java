package com.vestige.common.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vestige.common.domain.enumeration.QueryType;
import com.vestige.common.service.GeneralQueryService;
import com.vestige.common.service.dto.GeneralQueryDTO;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.core.exceptions.BadRequestAlertException;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing GeneralQuery.
 */
@RestController
@RequestMapping("/api/" + "${application.api.version}")
public class GeneralQueryResource {

    private final Logger log = LoggerFactory.getLogger(GeneralQueryResource.class);

    private static final String ENTITY_NAME = "commonGeneralQuery";

    private final GeneralQueryService generalQueryService;

    public GeneralQueryResource(GeneralQueryService generalQueryService) {
        this.generalQueryService = generalQueryService;
    }

    /**
     * POST  /general-queries : Create a new generalQuery.
     *
     * @param generalQueryDTO the generalQueryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new generalQueryDTO, or with status 400 (Bad Request) if the generalQuery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/general-queries")
    public ResponseEntity<GeneralQueryDTO> createGeneralQuery(@Valid @RequestBody GeneralQueryDTO generalQueryDTO) throws URISyntaxException {
        log.debug("REST request to save GeneralQuery : {}", generalQueryDTO);
        if (generalQueryDTO.getId() != null) {
            throw new BadRequestAlertException("A new generalQuery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralQueryDTO result = generalQueryService.save(generalQueryDTO);
        return ResponseEntity.created(new URI("/api/general-queries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /general-queries : Updates an existing generalQuery.
     *
     * @param generalQueryDTO the generalQueryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated generalQueryDTO,
     * or with status 400 (Bad Request) if the generalQueryDTO is not valid,
     * or with status 500 (Internal Server Error) if the generalQueryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/general-queries")
    public ResponseEntity<GeneralQueryDTO> updateGeneralQuery(@Valid @RequestBody GeneralQueryDTO generalQueryDTO) throws URISyntaxException {
        log.debug("REST request to update GeneralQuery : {}", generalQueryDTO);
        if (generalQueryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneralQueryDTO result = generalQueryService.save(generalQueryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, generalQueryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /general-queries : get all the generalQueries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of generalQueries in body
     */
    @GetMapping("/general-queries")
    public ResponseEntity<List<GeneralQueryDTO>> getAllGeneralQueries(Pageable pageable,
			@RequestParam(value = "q", required = false) String query) {
        log.debug("REST request to get a page of GeneralQueries");
        Page<GeneralQueryDTO> page = generalQueryService.findAll(pageable,query);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/general-queries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /general-queries/:id : get the "id" generalQuery.
     *
     * @param id the id of the generalQueryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the generalQueryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/general-queries/{id}")
    public ResponseEntity<GeneralQueryDTO> getGeneralQuery(@PathVariable String id) {
        log.debug("REST request to get GeneralQuery : {}", id);
        Optional<GeneralQueryDTO> generalQueryDTO = generalQueryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(generalQueryDTO);
    }

    /**
     * DELETE  /general-queries/:id : delete the "id" generalQuery.
     *
     * @param id the id of the generalQueryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/general-queries/{id}")
    public ResponseEntity<Void> deleteGeneralQuery(@PathVariable String id) {
        log.debug("REST request to delete GeneralQuery : {}", id);
        generalQueryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/general-queries?query=:query : search for the generalQuery corresponding
     * to the query.
     *
     * @param query the query of the generalQuery search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/general-queries")
    public ResponseEntity<List<GeneralQueryDTO>> searchGeneralQueries(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of GeneralQueries for query {}", query);
        Page<GeneralQueryDTO> page = generalQueryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/general-queries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /general-queries/:query-type : get the QueryType.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the QueryType, or with status 404 (Not Found)
     */
    @GetMapping("/general-queries/query-type")
    public ResponseEntity<List<QueryType>> getQueryTypes() {
        log.debug("REST request to get query-type ");
        return ResponseUtil.wrapOrNotFound(generalQueryService.getQueryTypes());
    }
}
