package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.HouseReportCD;
import com.lanswon.estate.bean.cd.RentReportCD;
import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/report")
@Api(tags = "报表")
public class ReportRest {

	@Resource
	private ReportService reportService;

	@PostMapping(value = "/rent")
	@ApiOperation(value = "租金报表")
	public DTO getRentReport(@RequestBody RentReportCD cd){
		return reportService.getRentReport(cd);
	}

	@PostMapping(value = "/house_resource")
	@ApiOperation(value = "房源报表")
	public DTO getHouseReport(@RequestBody ReportCD cd){
		return reportService.getHouseReport(cd);
	}


	@PostMapping(value = "/house_assets")
	@ApiOperation(value = "房产统计")
	public DTO getHouseAssetsReport(@RequestBody ReportCD cd){
		return reportService.getHouseAssetsReport(cd);
	}


}
