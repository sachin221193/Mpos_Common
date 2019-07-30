package com.vestige.common.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.Language;

/**
 * Spring Data MongoDB repository for the Language entity.
 */
@Repository
public interface LanguageRepository extends MongoRepository<Language,String> {
	
	Optional<Language> findOneByCode(String code);
    
}
