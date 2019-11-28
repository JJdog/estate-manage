package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicHouseUsage;
import com.lanswon.estate.service.dic.DicHouseUsageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 房屋_规划用途
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/house/usage")
@Api(tags = "字典-房屋规划用途")
public class DicHouseUsageRest {

	@Resource
	private DicHouseUsageService dicHouseUsageService;



	///////////用途///////////
	@PostMapping
	@ApiOperation(value = "新增用途情况")
	public DTO insertUsage(@RequestBody DicHouseUsage usage){
		return dicHouseUsageService.insertHouseUsage(usage);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除用途情况")
	public DTO deleteUsage(@PathVariable String id){
		return dicHouseUsageService.deleteHouseUsage(id);
	}

	@PutMapping
	@ApiOperation(value = "更新用途情况")
	public DTO updateUsage(@RequestBody DicHouseUsage usage){
		return dicHouseUsageService.updateHouseUsage(usage);
	}

	@GetMapping
	@ApiOperation(value = "获得所有用途情况")
	public DTO getAllUsageInfo(@RequestParam(value = "page") int page,
	                           @RequestParam(value = "limit")int limit,
	                           @RequestParam(value = "asc")int asc){
		Page<DicHouseUsage> pageCondition = new Page<>(page, limit);
		return dicHouseUsageService.getAllHouseUsageInfo(pageCondition,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有用途情况--不分页")
	public DTO getAllUsageInfo(){
		return dicHouseUsageService.getAllHouseUsageWithoutPage();
	}

}
