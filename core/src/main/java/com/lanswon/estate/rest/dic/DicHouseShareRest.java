package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.pojo.dic.DicHouseShare;
import com.lanswon.estate.service.dic.DicHouseShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 房屋——共有情况
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/house/share")
@Api(tags = "字典-房屋共有情况")
public class DicHouseShareRest {

	@Resource
	private DicHouseShareService dicHouseShareService;


	@PostMapping
	@ApiOperation(value = "新增共有类型情况")
	public DTO insertShareType(@RequestBody DicHouseShare houseShare){
		return dicHouseShareService.insertShareType(houseShare);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除共有情况")
	public DTO deleteShareType(@PathVariable String id){
		return dicHouseShareService.deleteShareType(id);
	}

	@PutMapping
	@ApiOperation(value = "更新共有情况")
	public DTO updateShareType(@RequestBody DicHouseShare houseShare){
		return dicHouseShareService.updateShareType(houseShare);
	}

	@GetMapping
	@ApiOperation(value = "获得所有共有情况")
	public DTO getAllShareTypeInfo(@RequestParam(value = "page") int page,
	                               @RequestParam(value = "limit")int limit,
	                               @RequestParam(value = "asc")int asc){
		Page<DicHouseShare> houseSharePage = new Page<>(page, limit);
		return dicHouseShareService.getAllShareTypeInfo(houseSharePage,asc);
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "获得所有")
	public DTO getAllShareTypeWithoutPage(){
		return dicHouseShareService.getAllShareTypeWithoutPage();
	}

}
