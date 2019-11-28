package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.dic.DicDealStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 合同状态Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface DicDealStatusMapper extends BaseMapper<DicDealStatus> {
}
