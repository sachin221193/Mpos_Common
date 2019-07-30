package com.vestige.common.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vestige.common.domain.AppFaq;

public interface AppFaqSearchRepository extends ElasticsearchRepository<AppFaq, String>{

}
