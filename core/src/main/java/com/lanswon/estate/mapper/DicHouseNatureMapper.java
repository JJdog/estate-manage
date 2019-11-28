package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicHouseNature;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产权类型
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicHouseNatureMapper extends BaseMapper<DicHouseNature> {
}
