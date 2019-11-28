package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.HouseAssetsCD;
import com.lanswon.estate.bean.pojo.HouseAssets;
import com.lanswon.estate.bean.vo.HouseAssetsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface HouseAssetsMapper extends BaseMapper<HouseAssets> {

	/**
	 * 获得房产信息分页
	 * @param page 分页
	 * @param asc 顺序
	 * @return HouseAssetsVO
	 */
	IPage<HouseAssetsVO> getHouseAssetsListPage(@Param("page") Page page,
	                                            @Param("asc")int asc,
	                                            @Param("cd")HouseAssetsCD cd );

	List<String> getAllSerialId();
}
