package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.HouseAssetsCD;
import com.lanswon.estate.bean.pojo.HouseAssets;
import com.lanswon.estate.service.HouseAssetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 房屋资产REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/assets/house")
@Api(tags = "房屋资产管理")
public class HouseAssetsController {

	@Resource
	private HouseAssetsService houseAssetsService;


	@PostMapping
	@ApiOperation(value = "新增房屋资产")
	public DTO insertHouseAssets(@RequestBody @Valid HouseAssets houseAssets){
		return houseAssetsService.insertHouseAssets(houseAssets);
	}

	@DeleteMapping("/{hid}")
	@ApiOperation(value = "删除房屋资产信息")
	public DTO deleteHouseAssets(@PathVariable String hid){
		return houseAssetsService.deleteHouseAssets(hid);
	}

	@PutMapping
	@ApiOperation(value = "修改房屋资产信息")
	public DTO updateHouseAssets(@RequestBody HouseAssets houseAssets){
		return houseAssetsService.updateHouseAssets(houseAssets);
	}

	@PostMapping(value = "/all")
	@ApiOperation(value = "获得房屋资产信息-分页")
	public DTO getHouseAssetsPage(@RequestBody HouseAssetsCD cd){
	return houseAssetsService.getHouseAssetsPage(cd);
	}

	@GetMapping(value = "/{hid}")
	@ApiOperation(value = "获得房产相信信息")
	public DTO getDetailHouseAssets(@PathVariable(value = "hid") Long hid){
		return houseAssetsService.getDetailHouseAssets(hid);
	}

	@GetMapping("/menu")
	@ApiOperation(value = "房屋资产信息-不分页")
	public DTO getHouseAssetsInfoWithoutPage(){
		return houseAssetsService.getHouseAssetsInfoWithoutPage();
	}

}
