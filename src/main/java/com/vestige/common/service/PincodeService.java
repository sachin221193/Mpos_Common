package com.vestige.common.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.common.web.rest.vm.DistributorLocationVm;
import com.vestige.common.web.rest.vm.LocationData;
import com.vestige.core.model.dto.PincodeDTO;

/**
 * Service Interface for managing Pincode.
 */
public interface PincodeService {

    /**
     * Save a pincode.
     *
     * @param pincodeDTO the entity to save
     * @return the persisted entity
     */
    PincodeDTO save(PincodeDTO pincodeDTO);

    /**
     * Get all the pincodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PincodeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pincode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PincodeDTO> findOne(String id);

    /**
     * Delete the "id" pincode.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get the "pinCode" pincode.
     *
     * @param pincode of the entity
     * @return the entity
     */
    PincodeDTO findByPinCode(String pincode);
    
    /**
     * Get Pincode on basis of CountryId , StateId, CityId .
     * @param CountryId , StateId, CityId
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PincodeDTO> findPincodeByCountryStateCity(Pageable pageable,Integer countryid, Integer stateid, Integer cityid);

    
    LocationData findLocationDataByPincode(String pincode);

	DistributorLocationVm findDistributorLocation(Integer countryId, Integer stateId, Integer cityId, String pincode);
    
}
