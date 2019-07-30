package com.vestige.common.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vestige.common.domain.Zone;
import com.vestige.common.repository.ZoneRepository;
import com.vestige.common.service.ZoneService;
import com.vestige.common.service.mapper.ZoneMapper;
import com.vestige.core.model.dto.ZoneDTO;

/**
 * Service Implementation for managing Zone.
 */
@Service
public class ZoneServiceImpl implements ZoneService {

	private final Logger log = LoggerFactory.getLogger(ZoneServiceImpl.class);

	private final ZoneRepository zoneRepository;

	private final ZoneMapper zoneMapper;

	public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
		this.zoneRepository = zoneRepository;
		this.zoneMapper = zoneMapper;
	}

	/**
	 * Save a zone.
	 *
	 * @param zoneDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ZoneDTO save(ZoneDTO zoneDTO) {
		log.debug("Request to save Zone : {}", zoneDTO);
		Zone zone = zoneMapper.toEntity(zoneDTO);
		zone = zoneRepository.save(zone);
		return zoneMapper.toDto(zone);
	}

	/**
	 * Get all the zones.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public Page<ZoneDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Zones");
		return zoneRepository.findAll(pageable).map(zoneMapper::toDto);
	}

	/**
	 * Get one zone by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	public Optional<ZoneDTO> findOne(String id) {
		log.debug("Request to get Zone : {}", id);
		return zoneRepository.findById(id).map(zoneMapper::toDto);
	}

	/**
	 * Delete the zone by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(String id) {
		log.debug("Request to delete Zone : {}", id);
		zoneRepository.deleteById(id);
	}

	/**
	 * Get the "zoneId" zone.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */

	@Override
	public ZoneDTO findByZoneId(Integer zoneId) {
		return zoneMapper.toDto(zoneRepository.findByZoneId(zoneId));
	}
}
