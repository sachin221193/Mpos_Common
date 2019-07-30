package com.vestige.common.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the AboutUs entity.
 */
public class StaticContentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String title;

	private String description;

	private Integer status;

	@NotNull
	private Instant createdOn;

	@NotNull
	private Long createdby;

	@NotNull
	private Long updatedBy;

	@NotNull
	private Instant updatedOn;

	@NotNull
	private Long deletedBy;

	@NotNull
	private Instant deletedOn;

	private String contentType;

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
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

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StaticContentDTO aboutUsDTO = (StaticContentDTO) o;
		if (aboutUsDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), aboutUsDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "AboutUsDTO{" + "id=" + getId() + ", title='" + getTitle() + "'" + ", description='" + getDescription()
				+ "'" + ", status=" + getStatus() + "}";
	}
}
