package com.lanswon.estate.rest;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.HouseResourceCD;
import com.lanswon.estate.bean.pojo.HouseResource;
import com.lanswon.estate.service.HouseResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 房源REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/hresource")
@Api(tags = "房源信息")
public class HouseResourceController {



	@Resource
	private HouseResourceService houseResourceService;


	@PostMapping
	@ApiOperation(value = "新增房源信息")
	public DTO insertHouseResource(@RequestBody @Valid HouseResource houseResource){
		return houseResourceService.insertHouseResource(houseResource);
	}

	@DeleteMapping("/{hid}")
	@ApiOperation(value = "删除房源信息")
	public DTO deleteHouseResource(@PathVariable(value = "hid") long hid){
		return houseResourceService.deleteHouseResource(hid);
	}

	@PutMapping
	@ApiOperation(value = "更新房源信息")
	public DTO updateHouseResource(@RequestBody HouseResource houseResource){
		return houseResourceService.updateHouseResource(houseResource);
	}


	@PostMapping("/all")
	@ApiOperation(value = "获得所有房源信息-分页")
	public DTO getHouseResourcePage(@RequestBody HouseResourceCD cd){
		return houseResourceService.getHouseResourcePage(cd);
	}

	@GetMapping("/{hid}")
	@ApiOperation(value = "获得房源详细信息房源信息")
	public DTO getHouseResourceDetail(@PathVariable(value = "hid") long hid){
		return houseResourceService.getHouseResourceDetail(hid);
	}

	@GetMapping("/simple")
	@ApiOperation(value = "获得房源的下拉菜单")
	public DTO getHouseResourceMenuInfo(){
		return houseResourceService.getHouseResourceMenuInfo();
	}

	@GetMapping("/simple/norent")
	@ApiOperation(value = "未租赁的房源")
	public DTO getNoRentHouseResource(){
		return houseResourceService.getNoRentHouseResource();
	}

}
