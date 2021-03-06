package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicLandUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DicLandUsageMapper extends BaseMapper<DicLandUsage> {

	@Select("SELECT concat(t.name,'$',t.id) FROM dic_land_usage t")
	List<String> getAllUsageAndId();

}
