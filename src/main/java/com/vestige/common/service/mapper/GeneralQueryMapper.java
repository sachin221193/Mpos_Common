package com.vestige.common.service.mapper;

import com.vestige.common.domain.*;
import com.vestige.common.service.dto.GeneralQueryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GeneralQuery and its DTO GeneralQueryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeneralQueryMapper extends EntityMapper<GeneralQueryDTO, GeneralQuery> {



    default GeneralQuery fromId(String id) {
        if (id == null) {
            return null;
        }
        GeneralQuery generalQuery = new GeneralQuery();
        generalQuery.setId(id);
        return generalQuery;
    }
}
