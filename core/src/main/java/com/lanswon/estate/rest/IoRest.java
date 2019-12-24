package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.ExportTransFlowCD;
import com.lanswon.estate.service.IoService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 导入导出
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/io")
@Api(tags = "导入导出")
public class IoRest {

	@Resource
	private IoService ioService;


	@PostMapping(value = "/land/in")
	public DTO importLandAssets(@RequestParam(value = "file") MultipartFile file){
		return ioService.importLandAssets(file);
	}

	@PostMapping(value = "/house/in")
	public DTO importHouseAssets(@RequestParam(value = "file") MultipartFile file){
		return ioService.importHouseAssets(file);
	}

	@PostMapping(value = "/resource/in")
	public DTO importHouseResource(@RequestParam(value = "file") MultipartFile file) throws IOException {
		return ioService.importHouseResource(file);
	}

	@PostMapping(value = "/deal/in")
	public DTO importDeal(@RequestParam(value = "file")MultipartFile file){
		return null;
	}

	@PostMapping(value = "/flow/in")
	public DTO importTransFlow(@RequestParam(value = "file")MultipartFile file){
		return ioService.importTransFlow(file);
	}

	@PostMapping(value = "/flow/out")
	public void exportTransFlow(HttpServletResponse response,
	                            @RequestBody ExportTransFlowCD cd) throws IOException {
		response.setHeader("content-Type", "application/vnd.ms-excel");
		//编码
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + "流水交易.xls");

		response.setCharacterEncoding("UTF-8");

		Workbook transFlow = ioService.exportTransFlow(cd.getDate());

		transFlow.write(response.getOutputStream());
	}

}
