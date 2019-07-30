package com.vestige.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.common.domain.enumeration.QueryType;
import com.vestige.common.service.dto.GeneralQueryDTO;

/**
 * Service Interface for managing GeneralQuery.
 */
public interface GeneralQueryService {

    /**
     * Save a generalQuery.
     *
     * @param generalQueryDTO the entity to save
     * @return the persisted entity
     */
    GeneralQueryDTO save(GeneralQueryDTO generalQueryDTO);

    /**
     * Get all the generalQueries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GeneralQueryDTO> findAll(Pageable pageable,String query);


    /**
     * Get the "id" generalQuery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GeneralQueryDTO> findOne(String id);

    /**
     * Delete the "id" generalQuery.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Search for the generalQuery corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GeneralQueryDTO> search(String query, Pageable pageable);
    
	public Optional<List<QueryType>> getQueryTypes() ;
}
