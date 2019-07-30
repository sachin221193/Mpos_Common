package com.vestige.common.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vestige.common.domain.City;
import com.vestige.common.domain.Country;
import com.vestige.common.domain.Pincode;
import com.vestige.common.domain.State;
import com.vestige.common.domain.Zone;
import com.vestige.common.repository.CityRepository;
import com.vestige.common.repository.CountryRepository;
import com.vestige.common.repository.PincodeRepository;
import com.vestige.common.repository.StateRepository;
import com.vestige.common.repository.ZoneRepository;
import com.vestige.common.service.PincodeService;
import com.vestige.common.service.mapper.CityMapper;
import com.vestige.common.service.mapper.CountryMapper;
import com.vestige.common.service.mapper.PincodeMapper;
import com.vestige.common.service.mapper.StateMapper;
import com.vestige.common.service.mapper.ZoneMapper;
import com.vestige.common.web.rest.vm.DistributorLocationVm;
import com.vestige.common.web.rest.vm.LocationData;
import com.vestige.core.enumeration.Message;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.exceptions.ErrorConstants;
import com.vestige.core.model.dto.PincodeDTO;

/**
 * Service Implementation for managing Pincode.
 */
@Service
public class PincodeServiceImpl implements PincodeService {

    private final Logger log = LoggerFactory.getLogger(PincodeServiceImpl.class);

    private final PincodeRepository pincodeRepository;

    private final PincodeMapper pincodeMapper;
    
    private final CityRepository cityRepository;
    
    private final CityMapper cityMapper;
    
    private final StateRepository stateRepository;
    
    private final StateMapper stateMapper;
    
    private final CountryRepository countryRepository;
    
    private final CountryMapper countryMapper;
    
    private final ZoneRepository zoneRepository;
    
    private final ZoneMapper zoneMapper;

    public PincodeServiceImpl(PincodeRepository pincodeRepository, PincodeMapper pincodeMapper,
    		 CityRepository cityRepository,
    		CityMapper cityMapper, StateRepository stateRepository, StateMapper stateMapper, 
    		CountryRepository countryRepository,CountryMapper countryMapper,
    		ZoneRepository zoneRepository,ZoneMapper zoneMapper ) {
        this.pincodeRepository = pincodeRepository;
        this.pincodeMapper = pincodeMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    /**
     * Save a pincode.
     *
     * @param pincodeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PincodeDTO save(PincodeDTO pincodeDTO) {
        log.debug("Request to save Pincode : {}", pincodeDTO);
        Pincode pincode = pincodeMapper.toEntity(pincodeDTO);
        pincode = pincodeRepository.save(pincode);
        return pincodeMapper.toDto(pincode);
    }

    /**
     * Get all the pincodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<PincodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pincodes");
        return pincodeRepository.findAll(pageable)
            .map(pincodeMapper::toDto);
    }


    /**
     * Get one pincode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<PincodeDTO> findOne(String id) {
        log.debug("Request to get Pincode : {}", id);
        return pincodeRepository.findById(id)
            .map(pincodeMapper::toDto);
    }

    /**
     * Delete the pincode by id.
     *ma
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Pincode : {}", id);
        pincodeRepository.deleteById(id);
    }

	@Override
	public PincodeDTO findByPinCode(String pincode) {
		PincodeDTO pincodeDTO = null;
		List<Pincode> pincodeList = pincodeRepository.findByPincode(pincode);
		if (CollectionUtils.isEmpty(pincodeList)) {
			throw new BadRequestAlertException(Message.PINCODE_DOES_NOT_EXIST.getMessage(), "pincode",
					ErrorConstants.ERR_VALIDATION);
		}
		Pincode pincodes = pincodeList.get(0);
		pincodeDTO = new PincodeDTO();
		pincodeDTO.setPincode(pincode);
		pincodeDTO.setCity(cityMapper.toDto(cityRepository.findByCityId(pincodes.getCityId())));
		pincodeDTO.setState(stateMapper.toDto(stateRepository.findByStateId(pincodes.getStateId())));
		pincodeDTO.setCountry(
				countryMapper.toDto(countryRepository.findByCountryIdAndStatus(pincodes.getCountryId(), Boolean.TRUE)));
		pincodeDTO.setZone(zoneMapper.toDto(zoneRepository.findByZoneId(pincodes.getZoneId())));
		return pincodeDTO;
	}
	/**
     * Get Pincode on basis of CountryId , StateId, CityId .
     * @param CountryId , StateId, CityId
     * @param pageable the pagination information
     * @return the list of entities
     */
	@Override
	public Page<PincodeDTO> findPincodeByCountryStateCity(Pageable pageable, Integer countryid, Integer stateid,
			Integer cityid) {
		log.debug("Get Pincode on basis of CountryId , StateId, CityId", countryid, stateid, cityid);
		List<Pincode> entityList = pincodeRepository.findByCountryIdAndStateIdAndCityId(countryid, stateid, cityid);
		Page<PincodeDTO> page = new PageImpl<PincodeDTO>(pincodeMapper.toDto(entityList) , pageable, entityList.size());
		return page;
	}

	@Override
	public LocationData findLocationDataByPincode(String pincode) {
		List<Pincode> pincodes = pincodeRepository.findByPincode(pincode);
		if (!CollectionUtils.isEmpty(pincodes)) {
			Country country = countryRepository.findByCountryIdAndStatus(pincodes.get(0).getCountryId(), Boolean.TRUE);
			Map<Integer, List<Pincode>> pincodesMap = pincodes.stream()
					.collect(Collectors.groupingBy(Pincode::getStateId));
			List<LocationData.State> states = new LinkedList<>();
			for (Integer stateId : pincodesMap.keySet()) {
				List<LocationData.City> cityInformation = new LinkedList<>();
				pincodesMap.get(stateId).forEach(pin -> {
					City city = cityRepository.findByCityId(pin.getCityId());
					if (Objects.nonNull(city)) {
						cityInformation.add(new LocationData.City(city.getCityId(), city.getCityName()));
					}
				});
				State stateData = stateRepository.findByStateId(stateId);
				if (!Objects.isNull(stateData)) {
					states.add(
							new LocationData.State(stateData.getStateId(), stateData.getStateName(), cityInformation));
				}
			}
			LocationData location = new LocationData();
			location.setCountry(new LocationData.Country(country.getCountryId(), country.getCountryName(), states));
			return location;
		} else {
			throw new BadRequestAlertException(Message.PINCODE_DOES_NOT_EXIST.getMessage(), "pincode",
					ErrorConstants.ERR_VALIDATION);
		}
	}

	@Override
	public DistributorLocationVm findDistributorLocation(Integer countryId, Integer stateId, Integer cityId,
			String pincode) {
		DistributorLocationVm location = new DistributorLocationVm();
		location.setPincode(pincode);
		location.setCityId(cityId);
		location.setStateId(stateId);
		location.setCountryId(countryId);
		City city = cityRepository.findByCityId(cityId);
		if (Objects.nonNull(city))
			location.setCityName(city.getCityName());
		State state = stateRepository.findByStateId(stateId);
		if (Objects.nonNull(state))
			location.setStateName(state.getStateName());
		Country country = countryRepository.findByCountryId(countryId);
		if (Objects.nonNull(country))
			location.setCountryName(country.getCountryName());

		List<Pincode> pincodes = pincodeRepository.findByPincode(pincode);
		if (!CollectionUtils.isEmpty(pincodes)) {
			Zone zone = zoneRepository.findByZoneId(pincodes.get(0).getZoneId());
			if (Objects.nonNull(zone)) {
				location.setZoneId(zone.getZoneId());
				location.setZoneName(zone.getZoneName());
			}else {
				location.setZoneName("NULL");
				location.setZoneId(2);
			}
		} else {
			location.setZoneName("NULL");
			location.setZoneId(2);
		}
		return location;
	}
}