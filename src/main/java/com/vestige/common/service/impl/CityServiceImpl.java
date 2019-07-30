package com.vestige.common.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vestige.common.domain.City;
import com.vestige.common.domain.Country;
import com.vestige.common.repository.CityRepository;
import com.vestige.common.repository.CountryRepository;
import com.vestige.common.repository.StateRepository;
import com.vestige.common.service.CityService;
import com.vestige.common.service.mapper.CityMapper;
import com.vestige.common.web.rest.vm.CityVM;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.model.dto.CityDTO;
import com.vestige.core.utils.StringsUtil;

/**
 * Service Implementation for managing City.
 */
@Service
public class CityServiceImpl implements CityService {

	private final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

	private final CityRepository cityRepository;

	private final CityMapper cityMapper;

	private final CountryRepository countryRepository;
	
	private final StateRepository stateRepository;

	public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper,
			 CountryRepository countryRepository, StateRepository stateRepository) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
	}

	/**
	 * Save a city.
	 *
	 * @param cityDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public CityDTO save(CityDTO cityDTO) {
		log.debug("Request to save City : {}", cityDTO);
		City city = cityMapper.toEntity(cityDTO);
		city = cityRepository.save(city);
		return cityMapper.toDto(city);
	}

	/**
	 * Get all the cities.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public Page<CityDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Cities");
		return cityRepository.findAll(pageable).map(cityMapper::toDto);
	}

	/**
	 * Get one city by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	public Optional<CityDTO> findOne(String id) {
		log.debug("Request to get City : {}", id);
		return cityRepository.findById(id).map(cityMapper::toDto);
	}

	/**
	 * Delete the city by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(String id) {
		log.debug("Request to delete City : {}", id);
		cityRepository.deleteById(id);
	}

	/**
	 * Get the "cityId" city.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	public CityDTO findByCityId(Integer cityId) {
		return cityMapper.toDto(cityRepository.findByCityId(cityId));
	}

	 /**
     * Get all the cities on basis of StateId.
     * @param id is the StateId
     * @param pageable the pagination information
     * @return the list of entities
     */
	@Override
	public Page<CityDTO> findAllCitiesByStateId(Pageable pageable,Integer stateId) {
		log.debug("Request to search for all cities on basis of states : {}", stateId);
		List<City> entityList = cityRepository.findByStateId(stateId);
		return new PageImpl<>(cityMapper.toDto(entityList) , pageable, entityList.size());
		 
	}

	/****
	 * @description find city on the basis of country, state and city name
	 * @param countryName, stateName, cityName
	 * @return city
	 */
	@Override
	public CityVM findByName(String countryName, String stateName, String cityName) {
		CityVM cityVM = null;
		if (!StringsUtil.isNullOrEmpty(countryName)) {
			Country country = countryRepository.findByCountryName(countryName);
			if (!Objects.isNull(country)) {
				cityVM = new CityVM();
				cityVM.setCountryId(country.getCountryId());
				com.vestige.common.domain.State state = stateRepository.findByStateName(stateName);
				if (!Objects.isNull(state) && state.getCountryId().equals(country.getCountryId())) {
					cityVM.setStateId(state.getStateId());
					List<City> city = cityRepository.findByCityName(cityName);
					if (!CollectionUtils.isEmpty(city) && city.get(0).getStateId().equals(state.getStateId())) {
						cityVM.setCityId(city.get(0).getCityId());
						return cityVM;
						
					}throw new BadRequestAlertException("No City Found on The given Data  " + cityName);
				}throw new BadRequestAlertException("No State Found on The given Data  " + state);
			}throw new BadRequestAlertException("No Country Found on The given Data  " + countryName);
		}throw new BadRequestAlertException("Country name Should not be empty or null");
	}

}
