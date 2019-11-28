package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicLandNature;
import com.lanswon.estate.service.dic.DicLandNatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 土地使用权类型
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/land/nature")
@Api(tags = "字典-土地使用类型")
public class DicLandNatureRest {


	@Resource
	private DicLandNatureService dicLandNatureService;

	@PostMapping
	@ApiOperation(value = "新增产权性质情况")
	public DTO insertLandNature(@RequestBody DicLandNature landNature){
		return dicLandNatureService.insertLandNature(landNature);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除产权性质情况")
	public DTO deleteLandNature(@PathVariable String id){
		return dicLandNatureService.deleteLandNature(id);
	}

	@PutMapping
	@ApiOperation(value = "更新产权性质情况")
	public DTO updateLandNature(@RequestBody DicLandNature landNature){
		return dicLandNatureService.updateLandNature(landNature);
	}

	@GetMapping
	@ApiOperation(value = "获得所有产权性质情况")
	public DTO getAllLandNatureInfo(@RequestParam(value = "page") int page,
	                               @RequestParam(value = "limit")int limit,
	                               @RequestParam(value = "asc")int asc){
		Page<DicLandNature> landNaturePage = new Page<>(page, limit);
		return dicLandNatureService.getAllLandNatureInfo(landNaturePage,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有产权性质情况")
	public DTO getAllLandNatureInfo(){
		return dicLandNatureService.getAllLandNatureWithoutPage();
	}


}
