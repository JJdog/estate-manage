package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicAssetsCo;
import com.lanswon.estate.service.dic.DicAssetsCoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 产权单位
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/assetsco")
@Api(tags = "字典-产权单位")
public class DicAssetsCoRest {


	@Resource
	private DicAssetsCoService dicAssetsCoService;


	@PostMapping
	@ApiOperation(value = "新增产权单位情况")
	public DTO insertAssetsCo(@RequestBody DicAssetsCo co){
		return dicAssetsCoService.insertAssetsCo(co);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除产权单位情况")
	public DTO deleteAssetsCo(@PathVariable String id){
		return dicAssetsCoService.deleteAssetsCo(id);
	}

	@PutMapping
	@ApiOperation(value = "更新产权单位情况")
	public DTO updateAssetsCo(@RequestBody DicAssetsCo co){
		return dicAssetsCoService.updateAssetsCo(co);
	}

	@GetMapping
	@ApiOperation(value = "获得所有产权单位情况")
	public DTO getAllAssetsCoInfo(@RequestParam(value = "page") int page,
	                        @RequestParam(value = "limit")int limit,
	                        @RequestParam(value = "asc")int asc){
		Page<DicAssetsCo> coPage = new Page<>(page, limit);
		return dicAssetsCoService.getAllAssetsCoInfo(coPage,asc);
	}

	@GetMapping("/co1")
	@ApiOperation(value = "获得所有产权单位情况")
	public DTO getAllAssetsCoInfo(){
		return dicAssetsCoService.getAllAssetsCoWithoutPage();
	}


}
