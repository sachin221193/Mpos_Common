package com.vestige.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vestige.common.domain.StaticContent;

/**
 * Spring Data MongoDB repository for the AboutUs entity.
 */
@Repository
public interface StaticContentRepository extends MongoRepository<StaticContent, String> {
	
	Page<StaticContent> findByStatusInOrderByCreatedOnDesc(Pageable pageable,List<Integer> status);

	List<StaticContent> findByStatusAndContentType(Integer status, String contentType);
	
	List<StaticContent> findByContentType(String type);
	
	Page<StaticContent> findByContentTypeAndStatusInOrderByCreatedOnDesc(Pageable pageable, String type,List<Integer> status);

}
