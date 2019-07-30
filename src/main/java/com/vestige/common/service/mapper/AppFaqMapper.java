package com.vestige.common.service.mapper;

import com.vestige.common.domain.*;
import com.vestige.common.service.dto.AppFaqDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppFaq and its DTO AppFaqDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppFaqMapper extends EntityMapper<AppFaqDTO, AppFaq> {



    default AppFaq fromId(String id) {
        if (id == null) {
            return null;
        }
        AppFaq appFaq = new AppFaq();
        appFaq.setId(id);
        return appFaq;
    }
}
