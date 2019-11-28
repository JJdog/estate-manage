package com.lanswon.estate.mapper.poi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.po.PoiTransFlow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface PoiTransFlowMapper extends BaseMapper<PoiTransFlow> {


	List<PoiTransFlow> getRealMoney(@Param("date") Date date);

}