package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicLandNature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产权性质
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicLandNatureMapper extends BaseMapper<DicLandNature> {

	@Select("SELECT concat(t.name,'$',t.id) FROM dic_land_nature t")
	List<String> getAllNatureAndId();
}
