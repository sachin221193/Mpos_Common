package com.vestige.common.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.core.model.dto.StateDTO;

/**
 * Service Interface for managing State.
 */
public interface StateService {

	/**
	 * Save a state.
	 *
	 * @param stateDTO the entity to save
	 * @return the persisted entity
	 */
	StateDTO save(StateDTO stateDTO);

	/**
	 * Get all the states.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<StateDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" state.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<StateDTO> findOne(String id);

	/**
	 * Delete the "id" state.
	 *
	 * @param id the id of the entity
	 */
	void delete(String id);

	/**
	 * Get the "stateId" state.
	 *
	 * @param stateId of the entity
	 * @return the entity
	 */
	StateDTO findByStateId(Integer stateId);
	
	/**
	 * Get all the states on basis of CountryId.
	 * @param countryId
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<StateDTO> findAllStatesByCountryId(Pageable pageable,Integer countryId);
}
