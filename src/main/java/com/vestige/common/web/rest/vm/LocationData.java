package com.vestige.common.web.rest.vm;

import java.util.List;

public class LocationData {

	private Country country;

	public static class Country {

		public Country() {
		}

		public Country(Integer countryId, String countryName, List<State> states) {
			this.countryId = countryId;
			this.countryName = countryName;
			this.states = states;
		}

		private Integer countryId;

		private String countryName;

		private List<State> states;

		public Integer getCountryId() {
			return countryId;
		}

		public void setCountryId(Integer countryId) {
			this.countryId = countryId;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public List<State> getStates() {
			return states;
		}

		public void setStates(List<State> states) {
			this.states = states;
		}
	}

	public static class State {

		public State() {
		}

		public State(Integer stateId, String stateName, List<City> cities) {
			this.stateId = stateId;
			this.stateName = stateName;
			this.cities = cities;
		}

		private Integer stateId;

		private String stateName;

		private List<City> cities;

		public Integer getStateId() {
			return stateId;
		}

		public void setStateId(Integer stateId) {
			this.stateId = stateId;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		public List<City> getCities() {
			return cities;
		}

		public void setCities(List<City> cities) {
			this.cities = cities;
		}
	}

	public static class City {

		public City() {
		}

		public City(Integer cityId, String cityName) {
			this.cityId = cityId;
			this.cityName = cityName;
		}

		private Integer cityId;

		private String cityName;

		public Integer getCityId() {
			return cityId;
		}

		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
