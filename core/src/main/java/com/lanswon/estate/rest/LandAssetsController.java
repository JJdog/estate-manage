package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.LandAssetsCD;
import com.lanswon.estate.bean.pojo.LandAssets;
import com.lanswon.estate.service.LandAssestsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 土地资产REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/assets/land")
@Api(tags = "土地资产管理")
public class LandAssetsController {


	@Resource
	private LandAssestsService landAssestsService;


	@PostMapping
	@ApiOperation(value = "新增土地资产")
	public DTO insertLandAssets(@RequestBody LandAssets landAssets) {
		return landAssestsService.insertLandAssets(landAssets);
	}

	@DeleteMapping("/{lid}")
	@ApiOperation(value = "删除土地资产信息")
	public DTO deleteLandAssets(@PathVariable String lid) {
		return landAssestsService.deleteLandAssets(lid);
	}

	@PutMapping
	@ApiOperation(value = "修改土地资产信息")
	public DTO updateLandAssets(@RequestBody LandAssets landAssets) {
		return landAssestsService.updateLandAssets(landAssets);
	}

	@PostMapping("/all")
	@ApiOperation(value = "获得土地资产信息-分页")
	public DTO getLandAssetsInfo(@RequestParam(value = "asc") int asc,
	                             @RequestBody LandAssetsCD cd) {

		return landAssestsService.getLandAssetsInfo(asc, cd);
	}

	@GetMapping("/simple")
	@ApiOperation(value = "获得土地的下拉菜单")
	public DTO getLandAssetsMenuInfo(){
		return landAssestsService.getLandAssetsMenuInfo();
	}


}
