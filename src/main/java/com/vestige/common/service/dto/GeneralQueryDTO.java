package com.vestige.common.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.vestige.common.domain.enumeration.QueryType;

/**
 * A DTO for the GeneralQuery entity.
 */
public class GeneralQueryDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    @NotNull
    private QueryType queryType;

    @NotNull
    private String query;
    
    private String answer;

    @NotNull
    private String emailId;

    private Long createdBy;

    private Instant createdOn;

    private Long updatedBy;

    private Instant updatedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
		return updatedBy;
	}
    
    public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
    
    public Instant getUpdatedOn() {
		return updatedOn;
	}
    
    public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}
    
    public String getAnswer() {
		return answer;
	}
    
    public void setAnswer(String answer) {
		this.answer = answer;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeneralQueryDTO generalQueryDTO = (GeneralQueryDTO) o;
        if (generalQueryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), generalQueryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeneralQueryDTO{" +
            "id=" + getId() +
            ", queryType='" + getQueryType() + "'" +
            ", query='" + getQuery() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            "}";
    }
}
