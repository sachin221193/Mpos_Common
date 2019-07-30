package com.vestige.common.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vestige.core.model.dto.LanguageDTO;

/**
 * Service Interface for managing Language.
 */
public interface LanguageService {

    /**
     * Save a language.
     *
     * @param language the entity to save
     * @return the persisted entity
     */
    LanguageDTO save(LanguageDTO languageDto);

    /**
     *  Get all the languages.
     *
     *  @return the list of entities
     */
	Page<LanguageDTO> findAll(Pageable pageable);
    /**
     *  Get the "id" language.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Optional<LanguageDTO> findOne(String code);

    /**
     *  Delete the "id" language.
     *
     *  @param id the id of the entity
     */
    void delete(String code);

}
