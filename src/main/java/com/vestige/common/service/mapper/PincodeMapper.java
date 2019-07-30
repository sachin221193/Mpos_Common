package com.vestige.common.service.mapper;

import com.vestige.common.domain.*;
import com.vestige.core.model.dto.PincodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pincode and its DTO PincodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PincodeMapper extends EntityMapper<PincodeDTO, Pincode> {


}
