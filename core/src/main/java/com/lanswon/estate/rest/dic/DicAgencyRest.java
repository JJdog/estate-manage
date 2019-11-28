package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicAgency;
import com.lanswon.estate.service.dic.DicAgencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理单位
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/agency")
@Api(tags = "字典-管理单位")
public class DicAgencyRest {


	@Resource
	private DicAgencyService dicAgencyService;


	@PostMapping
	@ApiOperation(value = "新增管理单位(机构)情况")
	public DTO insertAgency(@RequestBody DicAgency agency){
		return dicAgencyService.insertAgency(agency);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除管理单位(机构)情况")
	public DTO deleteAgency(@PathVariable String id){
		return dicAgencyService.deleteAgency(id);
	}

	@PutMapping
	@ApiOperation(value = "更新管理单位(机构)情况")
	public DTO updateAgency(@RequestBody DicAgency agency){
		return dicAgencyService.updateAgency(agency);
	}

	@GetMapping
	@ApiOperation(value = "获得所有管理单位(机构)情况")
	public DTO getAllAgencyInfo(@RequestParam(value = "page") int page,
	                            @RequestParam(value = "limit")int limit,
	                            @RequestParam(value = "asc")int asc){
		Page<DicAgency> agencyPage = new Page<>(page, limit);
		return dicAgencyService.getAllAgencyInfo(agencyPage,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有管理单位(机构)情况")
	public DTO getAllAgencyInfo(){
		return dicAgencyService.getAllAgencyWithoutPage();
	}

}
