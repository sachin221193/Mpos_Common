package com.vestige.common.service.mapper;

import org.mapstruct.Mapper;

import com.vestige.common.domain.Zone;
import com.vestige.core.model.dto.ZoneDTO;

/**
 * Mapper for the entity Zone and its DTO ZoneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ZoneMapper extends EntityMapper<ZoneDTO, Zone> {


}
