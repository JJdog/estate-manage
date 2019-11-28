package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.MoneyDepositMust;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 保证金Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface MoneyDepositMustMapper extends BaseMapper<MoneyDepositMust> {
}
