package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.RentCD;
import com.lanswon.estate.bean.pojo.MoneyRealFlow;
import com.lanswon.estate.service.RentChargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 租金REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/rent")
@Api(value = "租金",tags = "租金")
public class RentChargeRest {

	@Resource
	private RentChargeService rentChargeService;


	@GetMapping("/detail/{id}")
	@ApiOperation(value = "依据合同查看租金信息")
	@Deprecated
	public DTO getDetailRentChargeInfo(@PathVariable(value = "id") long id){
		return rentChargeService.getDetailRentChargeInfo(id);
	}

	@PostMapping(value = "/date")
	@ApiOperation(value = "依据时间查看要收的款项")
	public DTO getDetailRentChargeInfo(@RequestBody RentCD cd){
		return rentChargeService.getDetailRentChargeInfoByDate(cd);
	}


	@PutMapping("/pay")
	@ApiOperation(value = "缴费")
	public DTO pay4Rent(@RequestBody MoneyRealFlow moneyRealFlow){
		return rentChargeService.pay4Rent(moneyRealFlow);
	}

}
