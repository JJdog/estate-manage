package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用途Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicHouseUsageMapper extends BaseMapper<DicHouseUsage> {

	@Select("SELECT concat(t.name,'$',t.id) FROM dic_house_usage t")
	List<String> getAllUsageAndId();

}
