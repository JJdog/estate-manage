package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseUsage;
import com.lanswon.estate.bean.pojo.dic.DicResourceType;
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
public interface DicResourceTypeMapper extends BaseMapper<DicResourceType> {


}
