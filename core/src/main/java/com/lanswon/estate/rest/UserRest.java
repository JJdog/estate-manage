package com.lanswon.estate.rest;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.estate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户rest
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户API")
public class UserRest {



	@Resource
	private UserService userService;


	@PostMapping(value = "/depart/{uid}/{did}")
	@ApiOperation("绑定用户和部门")
	public DTO bindDepartment(@PathVariable(value = "uid")Long uid,
	                          @PathVariable(value = "did")Long did){
		return userService.bindDepartment(uid,did);
	}

	@DeleteMapping(value = "/depart/{uid}/{did}")
	@ApiOperation(value = "解绑部门")
	public DTO unbindDepartment(@PathVariable(value = "uid")Long uid,
	                            @PathVariable(value = "did")Long did){
		return userService.unbindDepartment(uid,did);
	}


	@GetMapping(value = "/{uid}")
	@ApiOperation(value = "依据用户id获得用户信息")
	public DTO getUserInfoByUid(@PathVariable(value = "uid")Long uid){
		return userService.getUserInfoByUid(uid);
	}
}
