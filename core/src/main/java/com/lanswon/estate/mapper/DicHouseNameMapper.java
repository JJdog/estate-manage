package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseName;
import com.lanswon.estate.bean.pojo.dic.DicHouseNature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产权类型
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicHouseNameMapper extends BaseMapper<DicHouseName> {


	@Select("SELECT concat(t.name,'$',t.id) FROM dic_house_name t")
	List<String> getAllNameAndId();

}
