package com.vestige.common.repository;

import com.vestige.common.domain.GeneralQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the GeneralQuery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralQueryRepository extends MongoRepository<GeneralQuery, String> {

}
