package com.vestige.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.Zone;

/**
 * Spring Data MongoDB repository for the Zone entity.
 */
@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {

	Zone findByZoneId(Integer zoneId);
}
