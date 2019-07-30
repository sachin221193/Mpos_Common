package com.vestige.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.City;

/**
 * Spring Data MongoDB repository for the City entity.
 */
@Repository
public interface CityRepository extends MongoRepository<City, String> {

	City findByCityId(Integer cityId);
	
	/**
     * Get all the cities on basis of StateId.
     * @param id is the StateId
     * @param pageable the pagination information
     * @return the list of entities
     */
	List<City> findByStateId(Integer stateId);

	List<City> findByCityName(String cityName);
}
