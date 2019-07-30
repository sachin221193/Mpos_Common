package com.vestige.common.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vestige.core.utils.StringsUtil;

/**
 * A DTO for the AppFaq entity.
 */
public class AppFaqDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7414343862289408437L;

	private String id;

	@NotNull
	@Size(max = 150,min=10)
	private String question;

	@NotNull
	private String answer;

	@NotNull
	private List<String> keywords;

//	private Instant createdOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		if (!StringsUtil.isNullOrEmpty(question))
			question = question.trim();
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		if (!StringsUtil.isNullOrEmpty(answer))
			answer = answer.trim();
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

}
