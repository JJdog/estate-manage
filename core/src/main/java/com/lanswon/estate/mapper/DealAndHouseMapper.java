package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.DealAndHouse;
import com.lanswon.estate.bean.pojo.backup.BackupMidDealHouse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DealAndHouseMapper extends BaseMapper<DealAndHouse> {

	List<BackupMidDealHouse> getDealAndHouseInfo2Backup(long id);
}
