package com.vestige.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.Country;

/**
 * Spring Data MongoDB repository for the Country entity.
 */
@Repository
public interface CountryRepository extends MongoRepository<Country, String> {

	Country findByCountryIdAndStatus(Integer countryId, Boolean status);
	
	Country findByCountryName(String countryName);
	
	Country findByCountryId(Integer countryId);
}
