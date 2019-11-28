package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicDealStatus;
import com.lanswon.estate.service.dic.DicDealStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 合同状态
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/deal/status")
@Api(tags = "字典-合同状态")
public class DicDealStatusRest {


	@Resource
	private DicDealStatusService dicDealStatusService;

	///////合同状态////////

	@PostMapping
	@ApiOperation(value = "新增合同状态情况")
	public DTO insertDealStatus(@RequestBody DicDealStatus dealStatus){
		return dicDealStatusService.insertDealStatus(dealStatus);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除合同状态情况")
	public DTO deleteDealStatus(@PathVariable String id){
		return dicDealStatusService.deleteDealStatus(id);
	}

	@PutMapping
	@ApiOperation(value = "更新合同状态情况")
	public DTO updateDealStatus(@RequestBody DicDealStatus dealStatus){
		return dicDealStatusService.updateDealStatus(dealStatus);
	}

	@GetMapping
	@ApiOperation(value = "获得所有合同状态情况")
	public DTO getAllDealStatusInfo(@RequestParam(value = "page") int page,
	                                @RequestParam(value = "limit")int limit,
	                                @RequestParam(value = "asc")int asc){
		Page<DicDealStatus> coPage = new Page<>(page, limit);
		return dicDealStatusService.getAllDealStatusInfo(coPage,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有合同状态情况")
	public DTO getAllDealStatusInfo(){
		return dicDealStatusService.getAllDealStatusWithoutPage();
	}

}
