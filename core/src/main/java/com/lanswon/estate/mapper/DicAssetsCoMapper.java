package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicAssetsCo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 产权单位
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicAssetsCoMapper extends BaseMapper<DicAssetsCo> {


	@Select("SELECT concat(t.name,'$',t.id) FROM dic_assets_co t")
	List<String> getAllNameAndId();

}
