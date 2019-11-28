package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicLandNature;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产权性质
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicLandNatureMapper extends BaseMapper<DicLandNature> {
}
