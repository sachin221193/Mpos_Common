package com.vestige.common.service.mapper;

import com.vestige.common.domain.*;
import com.vestige.core.model.dto.StateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity State and its DTO StateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StateMapper extends EntityMapper<StateDTO, State> {


}
