package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicHouseNature;
import com.lanswon.estate.service.dic.DicHouseNatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 房屋_性质
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/house/nature")
@Api(tags = "字典-房产性质")
public class DicHouseNatureRest {


	@Resource
	private DicHouseNatureService dicHouseNatureService;



	@PostMapping
	@ApiOperation(value = "新增产权类型情况")
	public DTO insertHouseNature(@RequestBody DicHouseNature type){
		return dicHouseNatureService.insertHouseNature(type);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除产权类型情况")
	public DTO deleteHouseNature(@PathVariable String id){
		return dicHouseNatureService.deleteHouseNature(id);
	}

	@PutMapping
	@ApiOperation(value = "更新产权类型情况")
	public DTO updateHouseNature(@RequestBody DicHouseNature type){
		return dicHouseNatureService.updateHouseNature(type);
	}

	@GetMapping
	@ApiOperation(value = "获得所有产权类型情况")
	public DTO getAllHouseNatureInfo(@RequestParam(value = "page") int page,
	                             @RequestParam(value = "limit")int limit,
	                             @RequestParam(value = "asc")int asc){
		Page<DicHouseNature> HouseNaturePage = new Page<>(page, limit);
		return dicHouseNatureService.getAllHouseNatureInfo(HouseNaturePage,asc);
	}


	@GetMapping("/all")
	@ApiOperation(value = "获得所有产权类型情况")
	public DTO getAllHouseNatureInfo(){
		return dicHouseNatureService.getAllHouseNatureWithoutPage();
	}


}
