package com.vestige.common.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vestige.common.config.TokenProvider;
import com.vestige.common.domain.StaticContent;
import com.vestige.common.domain.enumeration.ContentType;
import com.vestige.common.domain.enumeration.StaticContentStatus;
import com.vestige.common.repository.StaticContentRepository;
import com.vestige.common.search.StaticContentQueryBuilder;
import com.vestige.common.service.StaticContentService;
import com.vestige.common.service.dto.StaticContentDTO;
import com.vestige.common.service.mapper.StaticContentMapper;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.exceptions.ErrorConstants;

/**
 * Service Implementation for managing StaticContent.
 */
@Service
public class StaticContentServiceImpl implements StaticContentService {

	private final Logger log = LoggerFactory.getLogger(StaticContentServiceImpl.class);

	private final StaticContentRepository staticContentRepository;

	private final StaticContentMapper staticContentMapper;

	private final MongoTemplate mongoTemplate;
	
	public StaticContentServiceImpl(StaticContentRepository staticContentRepository,
			StaticContentMapper staticContentMapper,MongoTemplate mongoTemplate) {
		this.staticContentRepository = staticContentRepository;
		this.staticContentMapper = staticContentMapper;
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Save a staticContent.
	 *
	 * @param staticContentDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public StaticContentDTO save(StaticContentDTO staticContentDTO) {
		log.debug("Request to save StaticContent : {}", staticContentDTO);
		StaticContent staticContent = staticContentMapper.toEntity(staticContentDTO);
		if (!Objects.isNull(staticContentDTO.getStatus())) {
			staticContent.setStatus(0);
		}
		staticContent.setCreatedby(Long.valueOf(TokenProvider.getUserId()));
		staticContent.setCreatedOn(Instant.now());
		staticContent.setUpdatedBy(Long.valueOf(TokenProvider.getUserId()));
		staticContent.setUpdatedOn(Instant.now());
		staticContent = staticContentRepository.save(staticContent);
		return staticContentMapper.toDto(staticContent);
	}

	@Override
	public StaticContentDTO update(StaticContentDTO staticContentDTO) {
		log.debug("Request to update staticContent : {}", staticContentDTO);
		StaticContent staticContents = staticContentRepository.findById(staticContentDTO.getId()).orElse(null);
		if (Objects.isNull(staticContents)) {
			throw new BadRequestAlertException("No Data Found", "StaticContent", ErrorConstants.ERR_VALIDATION);
		}
		List<StaticContent> staticContentList = staticContentRepository
				.findByContentType(staticContentDTO.getContentType());
		StaticContent staticContent = staticContentMapper.toEntity(staticContentDTO);
		if (!staticContentDTO.getStatus().equals(staticContents.getStatus()) &&!CollectionUtils.isEmpty(staticContentList)) {
			for (StaticContent content : staticContentList) {
				if (content.getStatus().equals(2)) {
					content.setStatus(2);
				} else {
					content.setStatus(0);
				}
				staticContentRepository.save(content);
			}
			staticContent.setStatus(staticContentDTO.getStatus());
			staticContent.setCreatedby(staticContents.getCreatedby());
			staticContent.setCreatedOn(staticContents.getCreatedOn());
			staticContent.setUpdatedBy(Long.valueOf(TokenProvider.getUserId()));
			staticContent.setUpdatedOn(Instant.now());
			staticContent = staticContentRepository.save(staticContent);
		}
		return staticContentMapper.toDto(staticContent);
	}
	
	@Override
	public StaticContentDTO updateContent(StaticContentDTO staticContentDTO,Boolean isEdit) {
		StaticContent staticContent = staticContentRepository.findById(staticContentDTO.getId()).orElse(null);
		
		if (Objects.isNull(staticContent)) {
			throw new BadRequestAlertException("No Data Found", "StaticContent", ErrorConstants.ERR_VALIDATION);
		}
		if (staticContentDTO.getStatus().equals(StaticContentStatus.PENDING.getKey()) && !isEdit) {
			List<StaticContent> activeContent = staticContentRepository.findByStatusAndContentType(
					StaticContentStatus.ACCEPTED.getKey(), staticContentDTO.getContentType());
			if (activeContent.size() < 2) {
				throw new BadRequestAlertException("Atleast one content should be active.", "StaticContent",
						ErrorConstants.ERR_VALIDATION);
			}
		}
		
		if(!staticContent.getStatus().equals(staticContentDTO.getStatus()) && staticContentDTO.getStatus().equals(1)) {
			List<StaticContent> staticContentList = staticContentRepository
					.findByContentType(staticContentDTO.getContentType());
			staticContentList.stream().forEach(s -> {
				s.setStatus(StaticContentStatus.PENDING.getKey());
			});
			staticContentRepository.saveAll(staticContentList);
		}
		staticContentDTO.setCreatedOn(staticContent.getCreatedOn());
		staticContentDTO.setCreatedby(staticContent.getCreatedby());
		staticContent = staticContentMapper.toEntity(staticContentDTO);
		
		staticContent.setUpdatedBy(Long.valueOf(TokenProvider.getUserId()));
		staticContent.setUpdatedOn(Instant.now());
		return staticContentMapper.toDto(staticContentRepository.save(staticContent));
	}
	
	
	/**
	 * Get all the staticContent.
	 *
	 * @return the list of entities
	 */
	@Override
	public Page<StaticContentDTO> findAll(Pageable pageable, String query) {
		if ((!StringUtils.isEmpty(query) && !query.contains("status")) || StringUtils.isEmpty(query)) {
			query = "statuses=[0,1]," + query;
		}
		Query criteriaQuery = new StaticContentQueryBuilder().getQueryBuilder(query);
		List<StaticContent> staticContents = mongoTemplate.find(criteriaQuery, StaticContent.class);
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > staticContents.size() ? staticContents.size()
				: (start + pageable.getPageSize());
		Page<StaticContentDTO> pages = new PageImpl<StaticContentDTO>(
				staticContentMapper.toDto(staticContents.subList(start, end)), pageable, staticContents.size());
		return pages;

	}

	/**
	 * Get one staticContent by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	public Optional<StaticContentDTO> findOne(String id) {
		log.debug("Request to get StaticContent : {}", id);
		return staticContentRepository.findById(id).map(staticContentMapper::toDto);
	}

	/**
	 * Delete the staticContent by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(String id) {
		log.debug("Request to delete StaticContent : {}", id);
		StaticContent staticContent = staticContentRepository.findById(id).orElse(null);
		if (Objects.isNull(staticContent)) {
			throw new BadRequestAlertException("No Data Found on this Id", "StaticContent",
					ErrorConstants.ERR_VALIDATION);
		}
		if (!staticContent.getStatus().equals(1)) {
			staticContent.setContentType(staticContent.getContentType());
			staticContent.setCreatedby(staticContent.getCreatedby());
			staticContent.setCreatedOn(staticContent.getCreatedOn());
			staticContent.setDescription(staticContent.getDescription());
			staticContent.setId(staticContent.getId());
			staticContent.setTitle(staticContent.getTitle());
			staticContent.setUpdatedBy(staticContent.getUpdatedBy());
			staticContent.setUpdatedOn(staticContent.getUpdatedOn());
			staticContent.setStatus(2);
			staticContent.setDeletedBy(Long.valueOf(TokenProvider.getUserId()));
			staticContent.setDeletedOn(Instant.now());
			staticContentRepository.save(staticContent);
		} else {
			throw new BadRequestAlertException("Atleast One Content Should Be Active", "StaticContent",
					ErrorConstants.ERR_VALIDATION);
		}

	}

	@Override
	public StaticContentDTO getOne(String contentType) {
		List<StaticContent> staticContent = staticContentRepository.findByStatusAndContentType(1, contentType);
		if (CollectionUtils.isEmpty(staticContent)) {
			throw new BadRequestAlertException("No Data Found", "StaticContent", ErrorConstants.ERR_VALIDATION);
		}
		return staticContentMapper.toDto(staticContent.get(0));

	}
	
	@Override
	public List<String> getType() {
		List<String> typeList = new ArrayList<>();
		typeList.add(ContentType.ABOUT_US.getValue());
		typeList.add(ContentType.DELIVERY_CHARGES.getValue());
		return typeList;
	}
	
	
}
