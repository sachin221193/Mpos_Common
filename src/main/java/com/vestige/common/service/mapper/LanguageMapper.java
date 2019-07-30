package com.vestige.common.service.mapper;

import org.mapstruct.Mapper;
import com.vestige.common.domain.*;

import com.vestige.core.model.dto.LanguageDTO;

@Mapper(componentModel = "spring", uses = {})
public interface LanguageMapper extends EntityMapper<LanguageDTO, Language> {


}
