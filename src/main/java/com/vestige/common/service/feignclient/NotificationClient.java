package com.vestige.common.service.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vestige.core.model.dto.GeneralQueryMailDTO;

@FeignClient(value = "notification")
public interface NotificationClient {

	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/email/general-query")
	public void sendGeneralQueryMail(@Valid @RequestBody GeneralQueryMailDTO generalQueryDTO);

}
