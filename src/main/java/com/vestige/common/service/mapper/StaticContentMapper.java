package com.vestige.common.service.mapper;

import org.mapstruct.Mapper;

import com.vestige.common.domain.StaticContent;
import com.vestige.common.service.dto.StaticContentDTO;

/**
 * Mapper for the entity AboutUs and its DTO AboutUsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StaticContentMapper extends EntityMapper<StaticContentDTO, StaticContent> {



    default StaticContent fromId(String id) {
        if (id == null) {
            return null;
        }
        StaticContent staticContent = new StaticContent();
        staticContent.setId(id);
        return staticContent;
    }
}
