package com.vestige.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.core.model.dto.CountryDTO;

import java.util.Optional;

/**
 * Service Interface for managing Country.
 */
public interface CountryService {

    /**
     * Save a country.
     *
     * @param countryDTO the entity to save
     * @return the persisted entity
     */
    CountryDTO save(CountryDTO countryDTO);

    /**
     * Get all the countries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CountryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" country.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CountryDTO> findOne(String id);

    /**
     * Delete the "id" country.
     *
     * @param id the id of the entity
     */
    void delete(String id);

   /**
     * Get the "countryId" country.
     * 
     * @param countryId
     * @return
     */
    CountryDTO findByCountryId(Integer countryId);
}
