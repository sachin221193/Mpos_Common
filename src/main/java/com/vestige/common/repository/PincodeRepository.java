package com.vestige.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.Pincode;

/**
 * Spring Data MongoDB repository for the Pincode entity.
 */
@Repository
public interface PincodeRepository extends MongoRepository<Pincode, String> {

	List<Pincode> findByPincode(String pincode);
	
	/**
     * Get Pincode on basis of CountryId , StateId, CityId .
     * @param CountryId , StateId, CityId
     * @param pageable the pagination information
     * @return the list of entities
     */
	
	List<Pincode> findByCountryIdAndStateIdAndCityId(Integer countryId, Integer stateId, Integer cityId);
	
}
