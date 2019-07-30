package com.vestige.common.search;

import java.util.Objects;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.vestige.core.search.SearchCriteria;

public class ElasticAppFaqBuilder extends BaseElasticQueryBuilder {


	private static final String KEYWORD = "keyword";


	@Override
	public NativeSearchQueryBuilder build() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		for (SearchCriteria param : params) {
			switch (param.getOperation()) {
			case SearchCriteria.CONTAINS:
				switch (param.getKey()) {
				case KEYWORD:
					String parameter = param.getValue().toString().toLowerCase();
					if (Objects.nonNull(parameter) && parameter.contains(" ")) {
						String[] words = parameter.trim().split(" ");
						for (String word : words) {
							if(!word.equals("")) {
								word = word.trim();
								query.should(QueryBuilders.regexpQuery("keywords",
										".*\"" + word + "\".*")).boost(2.0f);
								query.should(QueryBuilders.regexpQuery("question",
										".*\"" + word + "\".*")).boost(1.0f);
							}
						}
					}
					query.should(QueryBuilders.regexpQuery("keywords",
							".*\"" + parameter + "\".*")).boost(2.0f);
					query.should(QueryBuilders.regexpQuery("question",
							".*\"" + parameter + "\".*")).boost(1.0f);
					break;
				default:
					query.filter(QueryBuilders.matchQuery(param.getKey(), String.valueOf(param.getValue())));
					break;
				}
				break;
			case SearchCriteria.SORTING:
				searchQuery.withSort(SortBuilders.fieldSort(param.getKey())
						.order(SortOrder.fromString(String.valueOf(param.getValue()))));
				break;
			default:
				break;
			}
		}
		return searchQuery.withQuery(query);
	}
}
