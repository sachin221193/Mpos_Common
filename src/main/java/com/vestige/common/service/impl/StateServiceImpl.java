package com.vestige.common.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vestige.common.domain.State;
import com.vestige.common.repository.StateRepository;
import com.vestige.common.service.StateService;
import com.vestige.common.service.mapper.StateMapper;
import com.vestige.core.model.dto.StateDTO;

/**
 * Service Implementation for managing State.
 */
@Service
public class StateServiceImpl implements StateService {

    private final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);

    private final StateRepository stateRepository;

    private final StateMapper stateMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    /**
     * Save a state.
     *
     * @param stateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = stateMapper.toEntity(stateDTO);
        state = stateRepository.save(state);
        return stateMapper.toDto(state);
    }

    /**
     * Get all the states.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<StateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all States");
        return stateRepository.findAll(pageable)
            .map(stateMapper::toDto);
    }


    /**
     * Get one state by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<StateDTO> findOne(String id) {
        log.debug("Request to get State : {}", id);
        return stateRepository.findById(id)
            .map(stateMapper::toDto);
    }

    /**
     * Delete the state by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.deleteById(id);
    }

	@Override
	public StateDTO findByStateId(Integer stateId) {
		return stateMapper.toDto(stateRepository.findByStateId(stateId));
	}
	
	/**
	 * Get all the states on basis of CountryId.
	 * @param countryId
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public Page<StateDTO> findAllStatesByCountryId(Pageable pageable, Integer countryId) {
		log.debug("Request to search for state list on basis of CountryId : {}", countryId);
		List<State> entityList = stateRepository.findByCountryId(countryId);
		return new PageImpl<>(stateMapper.toDto(entityList) , pageable, entityList.size());
	}
}
