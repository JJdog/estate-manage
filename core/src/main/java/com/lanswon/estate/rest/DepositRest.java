package com.lanswon.estate.rest;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.MoneyDepositReturn;
import com.lanswon.estate.service.DepositService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 保证金REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/deposit/")
@Api(value = "保证金",tags = "保证金")
public class DepositRest {

	@Resource
	private DepositService depositService;

	@PostMapping(value = "/return")
	@ApiOperation(value = "退还保证金")
	public DTO returnDeposit(@RequestBody MoneyDepositReturn depositReturn){
		return depositService.returnDeposit(depositReturn);
	}

}
