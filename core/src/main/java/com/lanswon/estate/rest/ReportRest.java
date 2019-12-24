package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 报表Rest
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/report")
@Api(tags = "报表")
public class ReportRest {

	@Resource
	private ReportService reportService;

	@PostMapping(value = "/rent/area")
	@ApiOperation(value = "租赁面积报表")
	public DTO getRentAreaReport(@RequestBody ReportCD cd){
		return reportService.getRentAreaReport(cd);
	}

	@PostMapping(value = "/rent/money")
	@ApiOperation(value = "租赁金钱报表")
	public DTO getRentMoneyReport(@RequestBody ReportCD cd){
		return reportService.getRentMoneyReport(cd);
	}

	@PostMapping(value = "/rent/house")
	@ApiOperation(value = "租赁金钱报表")
	public DTO getHouseReport(@RequestBody ReportCD cd){
		return reportService.getHouseReport(cd);
	}





}
