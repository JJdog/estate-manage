package com.lanswon.estate.mapper.poi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.po.PoiHouseResourcePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 导入房源信息
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface PoiHouseResourceMapper extends BaseMapper<PoiHouseResourcePO> {
}
