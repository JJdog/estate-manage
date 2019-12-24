package com.lanswon.estate.service;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.bean.vo.report.ReportHouseVO;
import com.lanswon.estate.bean.vo.report.ReportRentAreaVO;
import com.lanswon.estate.bean.vo.report.ReportRentMoneyVO;
import com.lanswon.estate.bean.vo.report.comp.CompLandArea;
import com.lanswon.estate.bean.vo.report.comp.CompRealArrears;
import com.lanswon.estate.bean.vo.report.comp.CompRealMoney;
import com.lanswon.estate.mapper.DicAgencyMapper;
import com.lanswon.estate.mapper.HouseResourceMapper;
import com.lanswon.estate.mapper.ReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报表
 *
 * @author jaswine
 */
@Service
@Slf4j
public class ReportService {

	@Resource
	private ReportMapper reportMapper;
	@Resource
	private DicAgencyMapper dicAgencyMapper;
	@Resource
	private HouseResourceMapper houseResourceMapper;


	public DTO getRentAreaReport(ReportCD cd) {

		log.info("获得租赁面积报表");
		List<ReportRentAreaVO> reports = reportMapper.getRentAreaReport(cd);

		if (reports.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),reports);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),reports);
	}


	public DTO getRentMoneyReport(ReportCD cd) {
		log.info("获得租金信息报表");

		List<ReportRentMoneyVO> rentMoneyReport = reportMapper.getRentMoneyReport(cd);

		if (rentMoneyReport.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),rentMoneyReport);
		}

		rentMoneyReport.forEach(item -> {

			// 实收租金
			CompRealMoney realMoneyByAgency = reportMapper.getCompRealMoneyByAgency(cd.getDate(), item.getAid());
			item.setMonthRealMoney(realMoneyByAgency.getMonthRealMoney());
			item.setYearRealMoney(realMoneyByAgency.getYearRealMoney());
			// 实收欠款
			CompRealArrears arrearsByAgency = reportMapper.getCompRealArrearsByAgency(cd.getDate(), item.getAid());
			item.setMonthArrears(arrearsByAgency.getMonthArrears());
			item.setYearArrears(arrearsByAgency.getYearArrears());
			// 到账率
			item.setRate(String.valueOf(100 * item.getYearRealMoney() / item.getYearMustMoney()));
		});

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),rentMoneyReport);
	}

	public DTO getHouseReport(ReportCD cd) {

		log.info("获得资产面积报表");

		List<ReportHouseVO> houseReport = reportMapper.getHouseReport(cd);

		houseReport.forEach(house->{
			// 土产
			CompLandArea landArea = reportMapper.getCompLandArea(house.getHid());
			house.setLandYzArea(landArea.getLandYzArea());
			house.setHouseWzArea(landArea.getLandWzArea());
			house.setLandTotalArea(landArea.getLandTotalArea());

			// 房源

			reportMapper.getCompResourceArea(house.getHid());
		});
		return null;
	}
}
