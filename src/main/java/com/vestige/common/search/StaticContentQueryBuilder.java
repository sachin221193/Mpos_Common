package com.vestige.common.search;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.vestige.core.search.SearchCriteria;
import com.vestige.core.utils.DateUtil;
import com.vestige.core.utils.StringsUtil;

public class StaticContentQueryBuilder extends BaseQueryBuilder{

	private static final String STATUSES = "statuses";

	private static final String STATUS = "status";
	
	private static final String CONTENT_TYPE = "contentType";

	private static final String TITLE = "title";

	private static final String CREATED_ON = "createdOn";

	@Override
	public Query build() {
		Query query = new Query();
		for (SearchCriteria param : params) {
			String parameter = param.getValue().toString().toLowerCase();
			switch (param.getOperation()) {
				case SearchCriteria.CONTAINS:
					switch (param.getKey()) {
						case STATUSES:
							query.addCriteria(Criteria.where("status").in(StringsUtil.getIdsFromString(String.valueOf(param.getValue()))));
							break;
						case STATUS:
							query.addCriteria(Criteria.where("status").is(Integer.valueOf(parameter)));
							break;	
						case CONTENT_TYPE:
							query.addCriteria(Criteria.where(CONTENT_TYPE).is(param.getValue()));
							break;
						case TITLE:	
							query.addCriteria(Criteria.where(TITLE).regex(".*"+param.getValue()+".*","i"));
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
}