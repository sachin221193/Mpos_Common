package com.vestige.common.repository.search;

import com.vestige.common.domain.GeneralQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the GeneralQuery entity.
 */
public interface GeneralQuerySearchRepository extends ElasticsearchRepository<GeneralQuery, String> {
}
