package com.lanswon.estate.mapper.poi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanswon.estate.bean.po.PoiHouseAssetsPO;
import com.lanswon.estate.bean.po.PoiTransFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 房产IO
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface PoiHouseAssetsMapper extends BaseMapper<PoiHouseAssetsPO> {

}
