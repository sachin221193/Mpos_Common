package com.vestige.common.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Query;

import com.vestige.core.search.SearchCriteria;

public class BaseQueryBuilder {

	public final List<SearchCriteria> params;

	public BaseQueryBuilder() {
		params = new ArrayList<>();
	}

	public BaseQueryBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Query getQueryBuilder(String search) {
		Pattern pattern = Pattern.compile(SearchCriteria.SEARCHING_PATTERN);
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			this.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		return this.build();
	}

	public Query build() {
		if (params.isEmpty()) {
			return null;
		}
		return new Query();
	}
}
