package com.lanswon.estate.rest;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常拦截
 *
 * @author jaswine
 */
@ControllerAdvice
public class ExceptionAdviceRest {


	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public DTO errorHandler(Exception e){
		return new SimpleRtnDTO(CustomRtnEnum.ERROR.getStatus(),e.getMessage());
	}

}
