package com.lanswon.estate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.MoneyDamageMust;
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
public interface MoneyDamageMustMapper extends BaseMapper<MoneyDamageMust> {



}
