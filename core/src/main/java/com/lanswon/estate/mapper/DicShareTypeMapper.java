package com.lanswon.estate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseShare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 字典
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicShareTypeMapper extends BaseMapper<DicHouseShare> {



}
