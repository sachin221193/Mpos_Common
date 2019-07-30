package com.vestige.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.common.service.dto.StaticContentDTO;

/**
 * Service Interface for managing AboutUs.
 */
public interface StaticContentService {

    /**
     * Save a aboutUs.
     *
     * @param aboutUsDTO the entity to save
     * @return the persisted entity
     */
    StaticContentDTO save(StaticContentDTO aboutUsDTO);

    /**
     * Get all the aboutuses.
     *
     * @return the list of entities
     */
    Page<StaticContentDTO> findAll(Pageable pageable, String query);

    /**
     * Get the "id" aboutUs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StaticContentDTO> findOne(String id);

    /**
     * Delete the "id" aboutUs.
     *
     * @param id the id of the entity
     */

	StaticContentDTO update(StaticContentDTO staticContentDTO);

	void delete(String id);

	StaticContentDTO getOne(String contentType);

	List<String> getType();
	
	StaticContentDTO updateContent(StaticContentDTO staticContentDTO,Boolean isEdit);

}
