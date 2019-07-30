package com.vestige.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.State;

/**
 * Spring Data MongoDB repository for the State entity.
 */
@Repository
public interface StateRepository extends MongoRepository<State, String> {

	State findByStateId(Integer stateId);
	
	/**
	 * Get all the states on basis of CountryId.
	 * @param countryId
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	List<State> findByCountryId(Integer countryId);
	
	State findByStateName(String stateName);
	
}
