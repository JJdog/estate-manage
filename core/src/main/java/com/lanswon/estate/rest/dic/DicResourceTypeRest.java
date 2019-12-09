package com.lanswon.estate.rest.dic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.bean.cd.SimpleCD;
import com.lanswon.estate.bean.pojo.dic.DicResourceType;
import com.lanswon.estate.service.dic.DicResourceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 土地——使用用途
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/dic/resource/type")
@Api(tags = "字典-房源类型")
public class DicResourceTypeRest {


	@Resource
	private DicResourceTypeService dicResourceTypeService;

	@PostMapping
	@ApiOperation(value = "新增房源类型情况")
	public DTO insertResourceType(@RequestBody DicResourceType ResourceType){
		return dicResourceTypeService.insertResourceType(ResourceType);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除房源类型情况")
	public DTO deleteResourceType(@PathVariable String id){
		return dicResourceTypeService.deleteResourceType(id);
	}

	@PutMapping
	@ApiOperation(value = "更新房源类型情况")
	public DTO updateResourceType(@RequestBody DicResourceType ResourceType){
		return dicResourceTypeService.updateResourceType(ResourceType);
	}

	@PostMapping("/all")
	@ApiOperation(value = "获得所有房源类型情况")
	public DTO getAllResourceTypeInfo(@RequestBody SimpleCD cd){
		Page<DicResourceType> ResourceTypePage = new Page<>(cd.getPage(), cd.getLimit());
		return dicResourceTypeService.getAllResourceTypeInfo(ResourceTypePage,cd.getAsc());
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有房源类型情况")
	public DTO getAllResourceTypeInfo(){
		return dicResourceTypeService.getAllResourceTypeWithoutPage();
	}

}
