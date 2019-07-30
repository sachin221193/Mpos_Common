package com.vestige.common.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A State.
 */
@Document(collection = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("state_id")
    private Integer stateId;

    @Field("state_code")
    private String stateCode;

    @Field("state_name")
    private String stateName;

    @Field("country_id")
    private Integer countryId;

    @Field("sort_order")
    private Integer sortOrder;

    @Field("status")
    private Boolean status;

    @Field("zone_id")
    private Integer zoneId;

    @Field("is_state")
    private Boolean isState;

    @Field("parent_id")
    private Integer parentId;

    @Field("created_by")
    private Long createdBy;

    @Field("created_on")
    private Instant createdOn;

    @Field("updated_by")
    private Long updatedBy;

    @Field("updated_on")
    private Instant updatedOn;
    
    @Field("region")
    private String region; 

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public State stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public State stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public State stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public State countryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public State sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean isStatus() {
        return status;
    }

    public State status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public State zoneId(Integer zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Boolean isIsState() {
        return isState;
    }

    public State isState(Boolean isState) {
        this.isState = isState;
        return this;
    }

    public void setIsState(Boolean isState) {
        this.isState = isState;
    }

    public Integer getParentId() {
        return parentId;
    }

    public State parentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public State createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public State createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public State updatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public State updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    public String getRegion() {
		return region;
	}
    
    public void setRegion(String region) {
		this.region = region;
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
        State state = (State) o;
        if (state.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), state.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + getId() +
            ", stateId=" + getStateId() +
            ", stateCode=" + getStateCode() +
            ", stateName='" + getStateName() + "'" +
            ", countryId=" + getCountryId() +
            ", sortOrder=" + getSortOrder() +
            ", status='" + isStatus() + "'" +
            ", zoneId=" + getZoneId() +
            ", isState='" + isIsState() + "'" +
            ", parentId=" + getParentId() +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
