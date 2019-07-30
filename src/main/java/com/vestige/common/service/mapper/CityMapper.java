package com.vestige.common.service.mapper;

import com.vestige.common.domain.*;
import com.vestige.core.model.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity City and its DTO CityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CityMapper extends EntityMapper<CityDTO, City> {


}
