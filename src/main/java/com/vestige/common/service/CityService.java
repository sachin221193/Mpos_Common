package com.vestige.common.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.common.web.rest.vm.CityVM;
import com.vestige.core.model.dto.CityDTO;

/**
 * Service Interface for managing City.
 */
public interface CityService {

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save
     * @return the persisted entity
     */
    CityDTO save(CityDTO cityDTO);

    /**
     * Get all the cities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" city.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CityDTO> findOne(String id);

    /**
     * Delete the "id" city.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get the "cityId" city.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CityDTO findByCityId(Integer cityId);
    
    /**
     * Get all the cities on basis of StateId.
     * @param id is the StateId
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CityDTO> findAllCitiesByStateId(Pageable pageable,Integer stateId);
    
    CityVM findByName(String countryName, String stateName, String cityName);
}
