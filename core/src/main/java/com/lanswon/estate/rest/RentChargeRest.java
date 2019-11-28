package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.service.RentChargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/rent")
@Api(value = "租金",tags = "租金")
public class RentChargeRest {

	@Resource
	private RentChargeService rentChargeService;

	@GetMapping("/detail/{id}")
	@ApiOperation(value = "依据合同查看租金信息")
	public DTO getDetailRentChargeInfo(@PathVariable(value = "id") long id){

		return rentChargeService.getDetailRentChargeInfo(id);
	}

	@PutMapping("/pay/{id}/{money}")
	@ApiOperation(value = "缴费")
	public DTO pay4Rent(@PathVariable(value = "id")long id,
	                    @PathVariable(value = "money")double money){
		return rentChargeService.pay4Rent(id,money);
	}

}
