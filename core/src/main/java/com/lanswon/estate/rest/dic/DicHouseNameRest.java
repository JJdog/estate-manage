package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.SimpleCD;
import com.lanswon.estate.bean.pojo.dic.DicHouseName;
import com.lanswon.estate.service.dic.DicHouseNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 房屋_性质
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/house/name")
@Api(tags = "字典-房产名称")
public class DicHouseNameRest {


	@Resource
	private DicHouseNameService dicHouseNameService;



	@PostMapping
	@ApiOperation(value = "新增产权名称情况")
	public DTO insertHouseName(@RequestBody DicHouseName type){
		return dicHouseNameService.insertHouseName(type);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除产权名称情况")
	public DTO deleteHouseName(@PathVariable String id){
		return dicHouseNameService.deleteHouseName(id);
	}

	@PutMapping
	@ApiOperation(value = "更新产权名称情况")
	public DTO updateHouseName(@RequestBody DicHouseName type){
		return dicHouseNameService.updateHouseName(type);
	}

	@PostMapping(value = "/all")
	@ApiOperation(value = "获得所有产权名称情况")
	public DTO getAllHouseNameInfo(@RequestBody SimpleCD cd){
		return dicHouseNameService.getAllHouseNameInfo(cd);
	}


	@GetMapping("/all")
	@ApiOperation(value = "获得所有产权名称下拉菜单")
	public DTO getAllHouseNameInfo(){
		return dicHouseNameService.getAllHouseNameWithoutPage();
	}


}
