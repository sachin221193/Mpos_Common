package com.vestige.common.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.vestige.common.domain.AppFaq;
import com.vestige.common.repository.AppFaqRepository;
import com.vestige.common.repository.search.AppFaqSearchRepository;
import com.vestige.common.search.ElasticAppFaqBuilder;
import com.vestige.common.service.AppFaqService;
import com.vestige.common.service.dto.AppFaqDTO;
import com.vestige.common.service.mapper.AppFaqMapper;
import com.vestige.core.exceptions.BadRequestAlertException;
import com.vestige.core.exceptions.ErrorConstants;

/**
 * Service Implementation for managing AppFaq.
 */
@Service
public class AppFaqServiceImpl implements AppFaqService {

    private final Logger log = LoggerFactory.getLogger(AppFaqServiceImpl.class);

    private static final String APP_FAQ_INDEX = "appfaq";

    private final AppFaqRepository appFaqRepository;

    private final AppFaqMapper appFaqMapper;
    
    private final AppFaqSearchRepository appFaqSearchRepository;

    public AppFaqServiceImpl(AppFaqRepository appFaqRepository, AppFaqMapper appFaqMapper,AppFaqSearchRepository appFaqSearchRepository) {
        this.appFaqRepository = appFaqRepository;
        this.appFaqMapper = appFaqMapper;
        this.appFaqSearchRepository = appFaqSearchRepository;
    }

    /**
     * Save a AppFaq.
     *
     * @param AppFaqDTO the entity to save
     * @return the persisted entity
     */
	@Override
	public AppFaqDTO save(AppFaqDTO appFaqDTO) {
		log.debug("Request to save AppContent : {}", appFaqDTO);
		checkPattern(appFaqDTO.getKeywords());
		AppFaq appFaq = appFaqMapper.toEntity(appFaqDTO);
//		appFaq.setCreatedOn(Instant.now());
		appFaqSearchRepository.save(appFaq);
		return appFaqMapper.toDto(appFaqRepository.save(appFaq));
	}

    /**
     * Get all the appFaqs.
     *
     * @return the list of entities
     */

	@Override
    public Page<AppFaqDTO> findAll(String keyword,Pageable pageable) {
        log.debug("Request to get all appFaqs");
		NativeSearchQueryBuilder queryBuilder = new ElasticAppFaqBuilder().getElasticQueryBuilder(keyword);
		SearchQuery searchQuery = queryBuilder.withIndices(APP_FAQ_INDEX).withTypes(APP_FAQ_INDEX)
				.withPageable(pageable).build();
		Page<AppFaqDTO> faqs = appFaqSearchRepository.search(searchQuery).map(appFaqMapper::toDto);
        return faqs;
    }



    /**
     * Get one appFaq by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<AppFaqDTO> findOne(String id) {
        log.debug("Request to get appFaq : {}", id);
        return appFaqRepository.findById(id)
            .map(appFaqMapper::toDto);
    }

    /**
     * Delete the appFaq by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete appFaq : {}", id);
        appFaqRepository.deleteById(id);
    }
    
    private void checkPattern(List<String> keywords) {
        Pattern pattern = Pattern.compile("[a-zA-Z 0-9]*");
        for(String keyword : keywords) {
        	int length = keyword.length();
        	if(length > 20 || length < 3) {
        		throw new BadRequestAlertException("Keywords Should Not be less than 3 and More Than 20 Characters", "AppFAQ",
						ErrorConstants.ERR_VALIDATION);
        	}
        	 Matcher matcher = pattern.matcher(keyword);
			if (!matcher.matches()) {
				throw new BadRequestAlertException("Special Character Should Not Be Included", "AppFAQ",
						ErrorConstants.ERR_VALIDATION);
			}
        }
    }
}
