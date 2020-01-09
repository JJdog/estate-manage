package com.lanswon.estate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.MoneyDamageMust;
import com.lanswon.estate.bean.pojo.MoneyDepositReturn;
import com.lanswon.estate.bean.vo.DepositReturnVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 字典
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface MoneyDepositReturnMapper extends BaseMapper<MoneyDepositReturn> {


	List<DepositReturnVO> getDepositReturnsByDealId(@Param("id") Long id);
}
