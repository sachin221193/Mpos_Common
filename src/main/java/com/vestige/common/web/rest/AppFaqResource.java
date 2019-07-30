package com.vestige.common.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.vestige.common.service.AppFaqService;
import com.vestige.common.service.dto.AppFaqDTO;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.core.exceptions.BadRequestAlertException;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing appFaq.
 */
@RestController
@RequestMapping("/api/" + "${application.api.version}")
public class AppFaqResource {

    private final Logger log = LoggerFactory.getLogger(AppFaqResource.class);

    private static final String ENTITY_NAME = "appFaq";

    private final AppFaqService appFaqService;

    public AppFaqResource(AppFaqService appFaqService) {
        this.appFaqService = appFaqService;
    }

    /**
     * POST  /app-faq : Create a new appFaq.
     *
     * @param appFaqDTO the appFaqDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appFaqDTO, or with status 400 (Bad Request) if the appFaq has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-faqs")
    @Timed
    public ResponseEntity<AppFaqDTO> createAppContent(@RequestBody AppFaqDTO appFaqDTO) throws URISyntaxException {
        log.debug("REST request to save appFaq : {}", appFaqDTO);
        if (appFaqDTO.getId() != null) {
            throw new BadRequestAlertException("A new appContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppFaqDTO result = appFaqService.save(appFaqDTO);
        return ResponseEntity.created(new URI("/api/app-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-faq : Updates an existing appContent.
     *
     * @param appFaqDTO the appFaqDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appFaqDTO,
     * or with status 400 (Bad Request) if the appFaqDTO is not valid,
     * or with status 500 (Internal Server Error) if the appFaqDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-faqs")
    @Timed
    public ResponseEntity<AppFaqDTO> updateAppContent(@RequestBody AppFaqDTO appFaqDTO) throws URISyntaxException {
        log.debug("REST request to update appFaq : {}", appFaqDTO);
        if (appFaqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppFaqDTO result = appFaqService.save(appFaqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appFaqDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-faqs : get all the appContents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of appFaq in body
     */
    @GetMapping("/app-faqs")
    @Timed
    public ResponseEntity<List<AppFaqDTO>> getAllAppContents(@RequestParam(required=false) String search,Pageable pageable) {
        log.debug("REST request to get all appFaqs");
		log.debug("REST request to get a page of Distributors");
		Page<AppFaqDTO> page = appFaqService.findAll(search, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/" + "${application.api.version}" + "/app-faqs");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /app-faqs/:id : get the "id" appContent.
     *
     * @param id the id of the appFaqDTO to retrieve"
     * @return the ResponseEntity with status 200 (OK) and with body the appFaqDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-faqs/{id}")
    @Timed
    public ResponseEntity<AppFaqDTO> getAppContent(@PathVariable String id) {
        log.debug("REST request to get appFaq : {}", id);
        Optional<AppFaqDTO> appContentDTO = appFaqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appContentDTO);
    }

    /**
     * DELETE  /app-faqs/:id : delete the "id" appFaq.
     *
     * @param id the id of the appFaqDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-faqs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppContent(@PathVariable String id) {
        log.debug("REST request to delete appFaq : {}", id);
        appFaqService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
