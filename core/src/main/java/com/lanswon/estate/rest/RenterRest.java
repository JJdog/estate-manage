package com.lanswon.estate.rest;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.Renter;
import com.lanswon.estate.service.RenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/renter")
@Api(tags = "企业信息")
public class RenterRest {

	@Resource
	private RenterService renterService;


	@PostMapping
	@ApiOperation(value = "新增管理单位(机构)情况")
	public DTO insertRenter(@RequestBody Renter Renter){
		return renterService.insertRenter(Renter);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除管理单位(机构)情况")
	public DTO deleteRenter(@PathVariable String id){
		return renterService.deleteRenter(id);
	}

	@PutMapping
	@ApiOperation(value = "更新管理单位(机构)情况")
	public DTO updateRenter(@RequestBody Renter Renter){
		return renterService.updateRenter(Renter);
	}

	@GetMapping
	@ApiOperation(value = "获得所有管理单位(机构)情况")
	public DTO getAllRenterInfo(@RequestParam(value = "page") int page,
	                            @RequestParam(value = "limit")int limit,
	                            @RequestParam(value = "asc")int asc){
		Page<Renter> renterPage = new Page<>(page, limit);
		return renterService.getAllRenterInfo(renterPage,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有管理单位(机构)情况")
	public DTO getAllRenterInfo(){
		return renterService.getAllRenterWithoutPage();
	}
}
