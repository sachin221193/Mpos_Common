package com.vestige.common.config.dbmigrations;

import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.vestige.common.domain.City;
import com.vestige.common.domain.Country;
import com.vestige.common.domain.Pincode;
import com.vestige.common.domain.State;
import com.vestige.common.domain.Zone;
import com.vestige.core.utils.StringsUtil;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "001")
public class InitialSetupMigration {
	
	private final Logger log = LoggerFactory.getLogger(InitialSetupMigration.class);
	
	@ChangeSet(order = "01", author = "initiator", id = "01-addCountries")
	public void addCountries(MongoTemplate mongoTemplate) {
		try {
			List<Map<String, Object>> maps = new ObjectMapper().readValue(
					new File(getClass().getResource("/db_migrations/country_001.json").getFile()),
					new TypeReference<List<Map<String, Object>>>() {
					});
			for (Map<String, Object> map : maps) {
				Country country = new Country();
				country.setId(String.valueOf(map.get("country_id")));
				country.setCountryId(Integer.valueOf(String.valueOf(map.get("country_id"))));
				country.setCountryCode(String.valueOf(map.get("country_code")));
				country.setCountryName(String.valueOf(map.get("country_name")));
				country.setSortOrder(Integer.valueOf(String.valueOf(map.get("sort_order"))));
				country.setStatus(Boolean.valueOf(String.valueOf(map.get("status"))));
				country.setCreatedOn(Instant.now());
				mongoTemplate.save(country);
			}
		} catch (Exception e) {
			log.error("Getting error in addCountries : {}", e.getMessage());
		}
	}
    
	@ChangeSet(order = "02", author = "initiator", id = "02-addStates")
	public void addStates(MongoTemplate mongoTemplate) {
		try {
			List<Map<String, Object>> maps = new ObjectMapper().readValue(
					new File(getClass().getResource("/db_migrations/state_001.json").getFile()),
					new TypeReference<List<Map<String, Object>>>() {
					});
			for (Map<String, Object> map : maps) {
				State state = new State();
				state.setId(String.valueOf(map.get("state_id")));
				state.setStateId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("state_id")))
						? Integer.valueOf(String.valueOf(map.get("state_id")))
						: 0);
				state.setCountryId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("country_id")))
						? Integer.valueOf(String.valueOf(map.get("country_id")))
						: 0);
				state.setStateCode(String.valueOf(map.get("state_code")));
				state.setStateName(String.valueOf(map.get("state_name")));
				state.setSortOrder(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("sort_order")))
						? Integer.valueOf(String.valueOf(map.get("sort_order")))
						: 0);
				state.setStatus(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("status")))
						? Boolean.valueOf(String.valueOf(map.get("status")))
						: Boolean.FALSE);
				state.setZoneId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("zone_id")))
						? Integer.valueOf(String.valueOf(map.get("zone_id")))
						: 0);
				state.setRegion(String.valueOf(map.get("region")));
				mongoTemplate.save(state);
			}
		} catch (Exception e) {
			log.error("Getting error in addStates : {}", e.getMessage());
		}
	}
    
	@ChangeSet(order = "03", author = "initiator", id = "03-addCities")
	public void addCities(MongoTemplate mongoTemplate) {
		try {

			List<Map<String, Object>> maps = new ObjectMapper().readValue(
					new File(getClass().getResource("/db_migrations/city_001.json").getFile()),
					new TypeReference<List<Map<String, Object>>>() {
					});
			for (Map<String, Object> map : maps) {
				City city = new City();
				city.setId(String.valueOf(map.get("city_id")));
				city.setCityId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("city_id")))
						? Integer.valueOf(String.valueOf(map.get("city_id")))
						: 0);
				city.setStateId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("state_id")))
						? Integer.valueOf(String.valueOf(map.get("state_id")))
						: 0);
				city.setCityCode(String.valueOf(map.get("city_code")));
				city.setCityName(String.valueOf(map.get("city_name")));
				city.setSortOrder(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("sort_order")))
						? Integer.valueOf(String.valueOf(map.get("sort_order")))
						: 0);
				city.setStatus(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("status")))
						? Boolean.valueOf(String.valueOf(map.get("status")))
						: Boolean.FALSE);
				city.setCreatedOn(Instant.now());
				mongoTemplate.save(city);
			}
		} catch (Exception e) {
			log.error("Getting error in addCities : {}", e.getMessage());
		}
	}
    
	@ChangeSet(order = "04", author = "initiator", id = "04-addPincodes")
	public void addPincodes(MongoTemplate mongoTemplate) {
		try {
			List<Map<String, Object>> maps = new ObjectMapper().readValue(
					new File(getClass().getResource("/db_migrations/pincode_001.json").getFile()),
					new TypeReference<List<Map<String, Object>>>() {
					});
			for (Map<String, Object> map : maps) {
				Pincode pincode = new Pincode();
				pincode.setId(String.valueOf(map.get("ID")));
				pincode.setCityId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("city_id")))
						? Integer.valueOf(String.valueOf(map.get("city_id")))
						: 0);
				pincode.setStateId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("state_id")))
						? Integer.valueOf(String.valueOf(map.get("state_id")))
						: 0);
				pincode.setCountryId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("country_id")))
						? Integer.valueOf(String.valueOf(map.get("country_id")))
						: 0);
				pincode.setPincode(String.valueOf(map.get("pincode")));
				pincode.setZoneId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("zone_id")))
						? Integer.valueOf(String.valueOf(map.get("zone_id")))
						: 0);
				pincode.setSubZoneId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("sub_zone_id")))
						? Integer.valueOf(String.valueOf(map.get("sub_zone_id")))
						: 0);
				pincode.setAreaId(!StringsUtil.isNullOrEmpty(String.valueOf(map.get("area")))
						? Integer.valueOf(String.valueOf(map.get("area")))
						: 0);
				pincode.setCreatedOn(Instant.now());
				mongoTemplate.save(pincode);
			}
		} catch (Exception e) {
			log.error("Getting error in addPincodes : {}", e.getMessage());
		}
	}
    
	@ChangeSet(order = "05", author = "initiator", id = "05-addZones")
	public void addZones(MongoTemplate mongoTemplate) {
		try {
			List<Map<String, Object>> maps = new ObjectMapper().readValue(
					new File(getClass().getResource("/db_migrations/zone_001.json").getFile()),
					new TypeReference<List<Map<String, Object>>>() {
					});
			for (Map<String, Object> map : maps) {
				Zone zone = new Zone();
				zone.setId(String.valueOf(map.get("zone_id")));
				zone.setZoneId(Integer.valueOf(String.valueOf(map.get("zone_id"))));
				zone.setZoneName(String.valueOf(map.get("zone_name")));
				zone.setCreatedOn(Instant.now());
				mongoTemplate.save(zone);
			}
		} catch (Exception e) {
			log.error("Getting error in addZones : {}", e.getMessage());
		}
	}
}
