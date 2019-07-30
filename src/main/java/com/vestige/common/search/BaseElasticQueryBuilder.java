package com.vestige.common.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.vestige.core.search.SearchCriteria;

public class BaseElasticQueryBuilder {

	public final List<SearchCriteria> params;

	public BaseElasticQueryBuilder() {
		params = new ArrayList<>();
	}

	public BaseElasticQueryBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public NativeSearchQueryBuilder getElasticQueryBuilder(String search) {
		Pattern pattern = Pattern.compile(SearchCriteria.SEARCHING_PATTERN);
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			this.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		return this.build();
	}

	public NativeSearchQueryBuilder build() {
		if (params.isEmpty()) {
			return null;
		}
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		return searchQuery.withQuery(QueryBuilders.matchAllQuery());
	}
}
