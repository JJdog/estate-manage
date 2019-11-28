package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseUsage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用途Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicHouseUsageMapper extends BaseMapper<DicHouseUsage> {
}
