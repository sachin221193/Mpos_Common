package com.vestige.common.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Pincode.
 */
@Document(collection = "pincode")
public class Pincode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("pincode")
    private String pincode;

    @Field("country_id")
    private Integer countryId;

    @Field("state_id")
    private Integer stateId;

    @Field("city_id")
    private Integer cityId;

    @Field("zone_id")
    private Integer zoneId;

    @Field("sub_zone_id")
    private Integer subZoneId;

    @Field("area_id")
    private Integer areaId;

    @Field("created_by")
    private Long createdBy;

    @Field("created_on")
    private Instant createdOn;

    @Field("updated_by")
    private Long updatedBy;

    @Field("updated_on")
    private Instant updatedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPincode() {
        return pincode;
    }

    public Pincode pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public Pincode countryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public Pincode stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Pincode cityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public Pincode zoneId(Integer zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getSubZoneId() {
        return subZoneId;
    }

    public Pincode subZoneId(Integer subZoneId) {
        this.subZoneId = subZoneId;
        return this;
    }

    public void setSubZoneId(Integer subZoneId) {
        this.subZoneId = subZoneId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public Pincode areaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Pincode createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Pincode createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public Pincode updatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public Pincode updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pincode pincode = (Pincode) o;
        if (pincode.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pincode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pincode{" +
            "id=" + getId() +
            ", pincode=" + getPincode() +
            ", countryId=" + getCountryId() +
            ", stateId=" + getStateId() +
            ", cityId=" + getCityId() +
            ", zoneId=" + getZoneId() +
            ", subZoneId=" + getSubZoneId() +
            ", areaId=" + getAreaId() +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
