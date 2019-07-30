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

import com.vestige.common.service.StaticContentService;
import com.vestige.common.service.dto.StaticContentDTO;
import com.vestige.common.web.rest.util.HeaderUtil;
import com.vestige.common.web.rest.util.PaginationUtil;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.exceptions.ErrorConstants;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AboutUs.
 */
@RestController
@RequestMapping("/api/"+"${application.api.version}")
public class StaticContentResource {

    private final Logger log = LoggerFactory.getLogger(StaticContentResource.class);

    private static final String ENTITY_NAME = "commonAboutUs";

    private final StaticContentService staticContentService;

    public StaticContentResource(StaticContentService staticContentService) {
        this.staticContentService = staticContentService;
    }

    /**
     * POST  /StaticContent : Create a new aboutUs.
     *
     * @param aboutUsDTO the aboutUsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aboutUsDTO, or with status 400 (Bad Request) if the aboutUs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/static-content")
    public ResponseEntity<StaticContentDTO> createStaticContent(@RequestBody StaticContentDTO aboutUsDTO) throws URISyntaxException {
        log.debug("REST request to save AboutUs : {}", aboutUsDTO);
        if (aboutUsDTO.getId() != null) {
            throw new BadRequestAlertException("A new aboutUs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaticContentDTO result = staticContentService.save(aboutUsDTO);
        return ResponseEntity.created(new URI("/api/aboutuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aboutuses : Updates an existing aboutUs.
     *
     * @param aboutUsDTO the aboutUsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aboutUsDTO,
     * or with status 400 (Bad Request) if the aboutUsDTO is not valid,
     * or with status 500 (Internal Server Error) if the aboutUsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/static-content")
    public ResponseEntity<StaticContentDTO> updateStaticContent(@RequestBody StaticContentDTO staticContentDTO,@RequestParam Boolean isEdit) throws URISyntaxException {
        log.debug("REST request to update AboutUs : {}", staticContentDTO.getId());
        if(staticContentDTO.getId() == null)
        	throw new BadRequestAlertException("No data Found", "StaticContent", ErrorConstants.ERR_VALIDATION);
        StaticContentDTO result = staticContentService.updateContent(staticContentDTO,isEdit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, staticContentDTO.getId())).body(result);
    }

	/**
	 * GET /StaticContent : get all the StaticContent.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of StaticContent
	 *         in body
	 */
	@GetMapping("/static-content")
	public ResponseEntity<List<StaticContentDTO>> getAllStaticContent(Pageable pageable,
			@RequestParam(value = "q", required = false) String query) {
		log.debug("REST request to get all Aboutuses");
		Page<StaticContentDTO> page = staticContentService.findAll(pageable, query);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				"/api/" + "${application.api.version}" + "/static-content");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
    
   /*
     * GET  /StaticContent/:id : get the "id" aboutUs.
     *
     * @param id the id of the aboutUsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aboutUsDTO, or with status 404 (Not Found)
     */
	@GetMapping("/static-content/{id}")
	public ResponseEntity<StaticContentDTO> getStaticContent(@PathVariable String id) {
		log.debug("REST request to get AboutUs : {}", id);
		Optional<StaticContentDTO> staticContentDTO = staticContentService.findOne(id);
		return ResponseUtil.wrapOrNotFound(staticContentDTO);
	}

    /**
     * DELETE  /StaticContent/:id : delete the "id" aboutUs.
     *
     * @param id the id of the aboutUsDTO to delete
     * @return t ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/static-content/{id}")
    public ResponseEntity<Void> deleteStaticContent(@PathVariable String id) {
        log.debug("REST request to delete AboutUs : {}", id);
        staticContentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
	@GetMapping("/static-content/type/{type}")
	public ResponseEntity<StaticContentDTO> findOneAboutUs(@PathVariable String type) {
		log.debug("REST request to get AboutUs : {}");
		StaticContentDTO staticContent = staticContentService.getOne(type);
		return new ResponseEntity<>(staticContent, HttpStatus.OK);
	}
	
	@GetMapping("/static-content/type")
	public ResponseEntity<List<String>> getContentType() {
		log.debug("Rest Request To Get Content Type");
		return new ResponseEntity<>(staticContentService.getType(), HttpStatus.OK);
		
	}

}
