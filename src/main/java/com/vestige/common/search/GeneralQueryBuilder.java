package com.vestige.common.search;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.vestige.common.domain.enumeration.QueryType;
import com.vestige.core.search.SearchCriteria;
import com.vestige.core.utils.DateUtil;

public class GeneralQueryBuilder extends BaseQueryBuilder {

	private static final String QUERY_TYPE = "queryType";

	private static final String CREATED_ON = "createdOn";

	@Override
	public Query build() {
		Query query = new Query();
		for (SearchCriteria param : params) {
			String parameter = param.getValue().toString().toLowerCase();
			switch (param.getOperation()) {
			case SearchCriteria.CONTAINS:
				switch (param.getKey()) {
				case QUERY_TYPE:
					query.addCriteria(
							Criteria.where(QUERY_TYPE).in(getIdsFromStringToString(String.valueOf(param.getValue()))));
					break;
				case CREATED_ON:
					String dateRangeValues[] = String.valueOf(param.getValue()).split("/");
					Instant fromDate = DateUtil.formatDateForFilter(dateRangeValues[0]);
					Instant toDate = DateUtil.formatDateForFilter(dateRangeValues[1]).plus(23, ChronoUnit.HOURS)
							.plus(59, ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS);
					query.addCriteria(Criteria.where(CREATED_ON).gte(fromDate).lte(toDate));
					break;
				}
				break;
			case SearchCriteria.SORTING:
				query.with(Sort.by(Direction.fromString(parameter), param.getKey()));
				break;
			}
		}
		return query;
	}

	public Set<QueryType> getIdsFromStringToString(String idsAsString) {
		String[] ids = idsAsString.replace("[", "").replace("]", "").split(",");
		Set<QueryType> idToSearch = new HashSet<>();
		for (String id : ids)
			idToSearch.add(QueryType.getQueryType(id.toUpperCase()));
		return idToSearch;
	}
}