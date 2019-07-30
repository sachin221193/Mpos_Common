package com.vestige.common.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A StaticContent.
 */
@Document(collection = "static_content")
public class StaticContent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field("title")
	private String title;

	@Field("description")
	private String description;

	@Field("status")
	private Integer status;

	@Field("created_on")
	private Instant createdOn;

	@Field("created_by")
	private Long createdby;

	@Field("updated_by")
	private Long updatedBy;

	@Field("updated_on")
	private Instant updatedOn;

	@Field("deleted_by")
	private Long deletedBy;

	@Field("deleted_on")
	private Instant deletedOn;

	@Field("content_type")
	private String contentType;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getId() {
		return id;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public StaticContent title(String title) {
		this.title = title;
		return this;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public StaticContent description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public StaticContent status(Integer status) {
		this.status = status;
		return this;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StaticContent aboutUs = (StaticContent) o;
		if (aboutUs.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), aboutUs.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "StaticContent{" + "id=" + getId() + ", title='" + getTitle() + "'" + ", description='"
				+ getDescription() + "'" + ", status=" + getStatus() + "}";
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Instant getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Instant deletedOn) {
		this.deletedOn = deletedOn;
	}
}
