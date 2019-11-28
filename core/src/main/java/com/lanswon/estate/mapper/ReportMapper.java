package com.lanswon.estate.mapper;

import com.lanswon.estate.bean.cd.RentReportCD;
import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.bean.vo.MonthRentChargeVO;
import com.lanswon.estate.bean.vo.report.ReportHouseAssetsVO;
import com.lanswon.estate.bean.vo.report.ReportHouseResourceVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportMapper {

	List<Object> getRentReport(@Param("cd") RentReportCD cd);

	MonthRentChargeVO getHouseReportByDate(@Param("date") Date time);

	ReportHouseAssetsVO getHouseAssetsReport(@Param("id") Long id);

	List<ReportHouseResourceVO> getHouseResourceReport(@Param("id") Long id,
	                                             @Param("cd") ReportCD cd);
}
