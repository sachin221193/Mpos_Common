package com.vestige.common.domain;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.vestige.common.domain.enumeration.QueryType;

/**
 * A GeneralQuery.
 */
@Document(collection = "general_query")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "generalquery")
public class GeneralQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("query_type")
    private QueryType queryType;

    @NotNull
    @Field("query")
    private String query;
    
    @Field("answer")
    private String answer;

    @NotNull
    @Field("email_id")
    private String emailId;

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

    public QueryType getQueryType() {
        return queryType;
    }

    public GeneralQuery queryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getQuery() {
        return query;
    }

    public GeneralQuery query(String query) {
        this.query = query;
        return this;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEmailId() {
        return emailId;
    }

    public GeneralQuery emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public GeneralQuery createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public GeneralQuery createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
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
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeneralQuery generalQuery = (GeneralQuery) o;
        if (generalQuery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), generalQuery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeneralQuery{" +
            "id=" + getId() +
            ", queryType='" + getQueryType() + "'" +
            ", query='" + getQuery() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            "}";
    }
}
