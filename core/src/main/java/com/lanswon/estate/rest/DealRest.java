package com.lanswon.estate.rest;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.DealCD;
import com.lanswon.estate.bean.cd.DealStopCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.service.DealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * 合同
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "deal")
@Api(value = "合同" ,tags = "合同")
public class DealRest {

	@Resource
	private DealService dealService;

	@PostMapping
	@ApiOperation(value = "新增合同")
	public DTO insertDeal(@RequestBody @Valid Deal deal){
		return dealService.insertDeal(deal);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "删除合同")
	public DTO insertDeal(@PathVariable(value = "id")long id){
		return dealService.deleteDeal(id);
	}


	@PutMapping
	@ApiOperation(value = "更新合同")
	public DTO updateDeal(@RequestBody Deal deal){
		return dealService.updateDeal(deal);
	}

	@PostMapping(value = "/all")
	@ApiOperation(value = "获得合同-分页")
	public DTO getDealInfoPage(@RequestBody DealCD cd){
		return dealService.getDealInfoPage(cd);
	}

	@Deprecated()
	@GetMapping(value = "/detail/{id}")
	@ApiOperation(value = "获得合同具体信息")
	public DTO getDetailDealInfo(@PathVariable(value = "id")long id){
		return dealService.getDetailDealInfo(id);
	}

	@PutMapping(value = "/review/{id}/{status}")
	@ApiOperation(value = "合同审核")
	public DTO reviewDeal(@PathVariable(value = "id") long id,
	                      @PathVariable(value = "status") int status){
		return dealService.reviewDeal(id,status);
	}

	@PutMapping(value = "/stop")
	@ApiOperation(value = "终止合同")
	public DTO stopDeal(@RequestBody DealStopCD cd){
		return dealService.stopDeal(cd.getId(),cd.getDate());
	}

	@PostMapping(value = "/all/noreview")
	@ApiOperation(value = "获得未审核的合同信息")
	public DTO getNoReviewDealInfoPage(@RequestBody DealCD cd){
		return dealService.getNoReviewDealInfoPage(cd);
	}
}
