package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicLandUsage;
import com.lanswon.estate.service.dic.DicLandUsageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 土地——使用用途
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/land/usage")
@Api(tags = "字典-土地用途(地类)")
public class DicLandUsageRest {


	@Resource
	private DicLandUsageService dicLandUsageService;

	@PostMapping
	@ApiOperation(value = "新增房产用途情况")
	public DTO insertLandUsage(@RequestBody DicLandUsage landUsage){
		return dicLandUsageService.insertLandUsage(landUsage);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除房产用途情况")
	public DTO deleteLandUsage(@PathVariable String id){
		return dicLandUsageService.deleteLandUsage(id);
	}

	@PutMapping
	@ApiOperation(value = "更新房产用途情况")
	public DTO updateLandUsage(@RequestBody DicLandUsage landUsage){
		return dicLandUsageService.updateLandUsage(landUsage);
	}

	@GetMapping
	@ApiOperation(value = "获得所有房产用途情况")
	public DTO getAllLandUsageInfo(@RequestParam(value = "page") int page,
	                                @RequestParam(value = "limit")int limit,
	                                @RequestParam(value = "asc")int asc){
		Page<DicLandUsage> landUsagePage = new Page<>(page, limit);
		return dicLandUsageService.getAllLandUsageInfo(landUsagePage,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有房产用途情况")
	public DTO getAllLandUsageInfo(){
		return dicLandUsageService.getAllLandUsageWithoutPage();
	}

}
