package com.lanswon.estate.mapper;

import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.bean.vo.report.ReportHouseVO;
import com.lanswon.estate.bean.vo.report.ReportRentAreaVO;
import com.lanswon.estate.bean.vo.report.ReportRentMoneyVO;
import com.lanswon.estate.bean.vo.report.comp.CompLandArea;
import com.lanswon.estate.bean.vo.report.comp.CompRealArrears;
import com.lanswon.estate.bean.vo.report.comp.CompRealMoney;
import com.lanswon.estate.bean.vo.report.comp.CompResourceArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 报表Repo
 *
 * @author jaswine
 */
@Repository
public interface ReportMapper {

	/** 租赁面积 */
	List<ReportRentAreaVO> getRentAreaReport(@Param("cd") ReportCD cd);

	/** 租金 */
	List<ReportRentMoneyVO> getRentMoneyReport(@Param("cd") ReportCD cd);

	CompRealMoney getCompRealMoneyByAgency(@Param("date") Date date,
	                                       @Param("aid") Long aid);

	CompRealArrears getCompRealArrearsByAgency(@Param("date") Date date,
	                                           @Param("aid") Long aid);

	List<ReportHouseVO> getHouseReport(@Param("cd") ReportCD cd);

	CompLandArea getCompLandArea(@Param("hid") Long hid);

	CompResourceArea getCompResourceArea(@Param("hid") Long hid);
}
