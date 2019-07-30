package com.vestige.common.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vestige.common.domain.Language;
import com.vestige.common.repository.LanguageRepository;
import com.vestige.common.service.LanguageService;
import com.vestige.common.service.mapper.LanguageMapper;
import com.vestige.core.model.dto.LanguageDTO;


/**
 * Service Implementation for managing Language.
 */
@Service
public class LanguageServiceImpl implements LanguageService{

    private final Logger log = LoggerFactory.getLogger(LanguageServiceImpl.class);

    private final LanguageRepository languageRepository;

    private final LanguageMapper languageMapper;
   
    public LanguageServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
       
    }

    /**
     * Save a language.
     *
     * @param language the entity to save
     * @return the persisted entity
     */
    @Override
    public LanguageDTO save(LanguageDTO languageDto) {
    	log.debug("Request to save Country : {}", languageDto);
		Language language = languageMapper.toEntity(languageDto);
		Optional<Language> language1 = languageRepository.findOneByCode(languageDto.getCode());
		if(!language1.isPresent()) {
			language.setCreatedOn(Instant.now());
			language.setModifiedOn(Instant.now());
			language = languageRepository.save(language);
		}else {
			language = language1.get();
		}
		return languageMapper.toDto(language);
    }

    /**
     *  Get all the languages.
     *
     *  @return the list of entities
     */
    @Override
    public Page<LanguageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Languages");
        return languageRepository.findAll(pageable).map(languageMapper::toDto);
    }

    /**
     *  Get one language by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Optional<LanguageDTO> findOne(String code) {
        log.debug("Request to get Language : {}", code);
        return languageRepository.findOneByCode(code).map(languageMapper::toDto);
    }

    /**
     *  Delete the  language by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String code) {
        log.debug("Request to delete Language : {}", code);
        languageRepository.deleteById(code);
    }
}
