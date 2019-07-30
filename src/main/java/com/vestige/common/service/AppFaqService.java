package com.vestige.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.common.service.dto.AppFaqDTO;

/**
 * Service Interface for managing AppFaq.
 */
public interface AppFaqService {

    /**
     * Save a AppFaq.
     *
     * @param appFaqDTO the entity to save
     * @return the persisted entity
     */
    AppFaqDTO save(AppFaqDTO appFaqDTO);

    /**
     * Get all the AppFaqs.
     *
     * @return the list of entities
     */
    Page<AppFaqDTO> findAll(String keyword,Pageable pageable);

    /**
     * Get the "id" AppFaq.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AppFaqDTO> findOne(String id);

    /**
     * Delete the "id" AppFaq.
     *
     * @param id the id of the entity
     */
    void delete(String id);

}
