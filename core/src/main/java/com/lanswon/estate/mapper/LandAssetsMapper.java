package com.lanswon.estate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.LandAssetsCD;
import com.lanswon.estate.bean.pojo.LandAssets;
import com.lanswon.estate.bean.vo.LandAssetsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 土地资产mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface LandAssetsMapper extends BaseMapper<LandAssets> {


	IPage<LandAssetsVO> getLandAssetsPageList(@Param("page") Page page, @Param("asc") int asc, @Param("cd")LandAssetsCD cd);

	@Select("SELECT concat(t.land_no,'$',t.id) FROM land_assets t")
	List<String> getAllLandNoAndId();

}
