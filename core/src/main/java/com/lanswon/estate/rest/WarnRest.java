package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.WarnCD;
import com.lanswon.estate.service.WarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/warn")
@Api(tags = "预警")
public class WarnRest {

	@Resource
	private WarnService warnService;

	@PostMapping(value = "/deal")
	@ApiOperation(value = "合同预警")
	public DTO getDealWarnPage(@RequestBody WarnCD cd){
		return warnService.getDealWarnPage(cd);
	}



	@PostMapping(value = "/rent")
	@ApiOperation(value = "租金预警")
	public DTO getRentWarnPage(@RequestBody WarnCD cd){
		return warnService.getRentWarnPage(cd);
	}


}
