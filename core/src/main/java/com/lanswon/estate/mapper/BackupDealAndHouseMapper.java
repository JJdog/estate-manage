package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.DealAndHouse;
import com.lanswon.estate.bean.pojo.backup.BackupMidDealHouse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BackupDealAndHouseMapper extends BaseMapper<BackupMidDealHouse> {
}
