package com.vestige.common.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vestige.common.config.TokenProvider;
import com.vestige.common.domain.GeneralQuery;
import com.vestige.common.domain.enumeration.QueryType;
import com.vestige.common.repository.GeneralQueryRepository;
import com.vestige.common.repository.search.GeneralQuerySearchRepository;
import com.vestige.common.search.GeneralQueryBuilder;
import com.vestige.common.service.GeneralQueryService;
import com.vestige.common.service.dto.GeneralQueryDTO;
import com.vestige.common.service.feignclient.NotificationClient;
import com.vestige.common.service.mapper.GeneralQueryMapper;
import com.vestige.core.model.dto.GeneralQueryMailDTO;

/**
 * Service Implementation for managing GeneralQuery.
 */
@Service
public class GeneralQueryServiceImpl implements GeneralQueryService {

    private final Logger log = LoggerFactory.getLogger(GeneralQueryServiceImpl.class);

    private final GeneralQueryRepository generalQueryRepository;

    private final GeneralQueryMapper generalQueryMapper;

    private final GeneralQuerySearchRepository generalQuerySearchRepository;
    
	private final MongoTemplate mongoTemplate;
	
	private final NotificationClient notificationClient;

    public GeneralQueryServiceImpl(GeneralQueryRepository generalQueryRepository, GeneralQueryMapper generalQueryMapper, GeneralQuerySearchRepository generalQuerySearchRepository,MongoTemplate mongoTemplate,NotificationClient notificationClient) {
        this.generalQueryRepository = generalQueryRepository;
        this.generalQueryMapper = generalQueryMapper;
        this.generalQuerySearchRepository = generalQuerySearchRepository;
        this.mongoTemplate = mongoTemplate;
        this.notificationClient = notificationClient;
    }

    /**
     * Save a generalQuery.
     *
     * @param generalQueryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GeneralQueryDTO save(GeneralQueryDTO generalQueryDTO) {
    	GeneralQuery generalQuery = null;
    	log.debug("Request to save GeneralQuery : {}", generalQueryDTO);
        if(!StringUtils.isEmpty(generalQueryDTO.getId())) {
        	generalQuery = generalQueryRepository.findById(generalQueryDTO.getId()).orElse(null);
        }
        if(Objects.isNull(generalQuery)) {
        	 generalQueryDTO.setCreatedOn(Instant.now());
        	 generalQueryDTO.setCreatedBy(Long.valueOf(TokenProvider.getUserId()));
             generalQuery = generalQueryMapper.toEntity(generalQueryDTO);
             
        }else {
            generalQuery.setUpdatedBy(Long.valueOf(TokenProvider.getUserId()));
            generalQuery.setUpdatedOn(Instant.now());
            generalQuery.setAnswer(generalQueryDTO.getAnswer());
			notificationClient.sendGeneralQueryMail(new GeneralQueryMailDTO(generalQueryDTO.getQuery(),
					generalQueryDTO.getEmailId(), generalQueryDTO.getAnswer()));
        }
        generalQuery = generalQueryRepository.save(generalQuery);
        generalQuerySearchRepository.save(generalQuery);
        return generalQueryMapper.toDto(generalQuery);
    }

    /**
     * Get all the generalQueries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<GeneralQueryDTO> findAll(Pageable pageable,String query) {
        log.debug("Request to get all GeneralQueries");
        Query criteriaQuery = new GeneralQueryBuilder().getQueryBuilder(query);
		List<GeneralQuery> staticContents = mongoTemplate.find(criteriaQuery, GeneralQuery.class);
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > staticContents.size() ? staticContents.size()
				: (start + pageable.getPageSize());
		Page<GeneralQueryDTO> pages = new PageImpl<GeneralQueryDTO>(
				generalQueryMapper.toDto(staticContents.subList(start, end)), pageable, staticContents.size());
		return pages;
        
    }


    /**
     * Get one generalQuery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<GeneralQueryDTO> findOne(String id) {
        log.debug("Request to get GeneralQuery : {}", id);
        return generalQueryRepository.findById(id)
            .map(generalQueryMapper::toDto);
    }

    /**
     * Delete the generalQuery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete GeneralQuery : {}", id);
        generalQueryRepository.deleteById(id);
        generalQuerySearchRepository.deleteById(id);
    }

    /**
     * Search for the generalQuery corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<GeneralQueryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of GeneralQueries for query {}", query);
        return generalQuerySearchRepository.search(queryStringQuery(query), pageable)
            .map(generalQueryMapper::toDto);
    }

	@Override
	public Optional<List<QueryType>> getQueryTypes() {
		
		return Optional.of(Arrays.stream(QueryType.values()).collect(Collectors.toList()));
	}
}
