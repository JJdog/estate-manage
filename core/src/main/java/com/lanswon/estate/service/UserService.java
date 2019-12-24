package com.lanswon.estate.service;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.dto.SimpleUserDTO;
import com.lanswon.estate.bean.pojo.MidUserInfo;
import com.lanswon.estate.mapper.UserMapper;
import com.lanswon.estate.provider.UumProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.rmi.server.UID;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

	@Resource
	private UumProvider uumProvider;
	
	@Resource
	private UserMapper userMapper;


	public DTO bindDepartment(Long uid, Long did) {
		log.info("绑定用户和部门");
		// 删除原有的
		Map<String, Object> map = new HashMap<>();
		map.put("fk_user_id",uid);
		userMapper.deleteByMap(map);
		// 新增
		MidUserInfo userInfo = new MidUserInfo(uid, did);
		userInfo.setCreatedTime(new Date());
		if (userMapper.insert(userInfo) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getUserInfoByUid(Long uid) {
		SimpleUserDTO userDTO = uumProvider.getSimpleUserInfoByUid(uid).getData();
		if (userMapper.getAgencyByUid(uid) == null){
			return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),null);
		}
		userDTO.setAgency(userMapper.getAgencyByUid(uid).getAgency());
		userDTO.setAgencyId(userMapper.getAgencyByUid(uid).getAgencyId());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),userDTO);

	}

	public DTO unbindDepartment(Long uid, Long did) {
		log.info("解绑用户{}和部门{}",uid,did);
		if (!userMapper.unbindDepartment(uid,did)){
			log.error("解绑用户和部门失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}
}

