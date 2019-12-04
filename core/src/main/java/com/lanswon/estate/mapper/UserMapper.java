package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.dto.SimpleUserDTO;
import com.lanswon.estate.bean.pojo.MidUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<MidUserInfo> {

	@Select("SELECT t1.name AS agency ,t.fk_agency_id AS agencyId FROM mid_user_info t LEFT JOIN dic_agency t1 ON t.fk_agency_id = t1.id WHERE t.fk_user_id = #{uid} ")
	SimpleUserDTO getAgencyByUid(Long uid);
}
