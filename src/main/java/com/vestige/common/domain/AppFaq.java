package com.vestige.common.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AppFaq.
 */
@Document(collection = "app_faq")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "appfaq", type = "appfaq")
public class AppFaq{

	@Id
	private String id;

	@Field("question")
	@org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Text, fielddata = true)
	private String question;

	@Field("answer")
	private String answer;

	@Field("keywords")
	@org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword, fielddata = true)
	private List<String> keywords;

//	@Field("created_on")
//	private Instant createdOn;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public AppFaq question(String question) {
		this.question = question;
		return this;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public AppFaq answer(String answer) {
		this.answer = answer;
		return this;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

//	public Instant getCreatedOn() {
//		return createdOn;
//	}
//
//	public void setCreatedOn(Instant createdOn) {
//		this.createdOn = createdOn;
//	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

}
