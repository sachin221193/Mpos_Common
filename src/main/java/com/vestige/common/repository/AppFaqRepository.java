package com.vestige.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.AppFaq;


/**
 * Spring Data MongoDB repository for the AppFaq entity.
 */
@Repository
public interface AppFaqRepository extends MongoRepository<AppFaq, String> {
	
}
