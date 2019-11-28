package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicAgency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理单位(机构)Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicAgencyMapper extends BaseMapper<DicAgency> {


	List<String> getAllNameAndId();

	@Select("SELECT id FROM dic_agency")
	List<Long> getAllAgencyId();
}
