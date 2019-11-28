package com.lanswon.estate.service;


import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.cd.RentReportCD;
import com.lanswon.estate.bean.cd.ReportCD;
import com.lanswon.estate.bean.vo.report.ReportHouseAssetsVO;
import com.lanswon.estate.bean.vo.report.ReportHouseResourceVO;
import com.lanswon.estate.mapper.DicAgencyMapper;
import com.lanswon.estate.mapper.HouseResourceMapper;
import com.lanswon.estate.mapper.ReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

	public DTO getRentReport(RentReportCD cd) {

		log.info("获得租金报表");


		reportMapper.getRentReport(cd);


		return null;
	}


	public DTO getHouseReport(ReportCD cd) {
		log.info("获得房源报表");

		// 0.空就是查所有
		if (cd.getAgency().isEmpty()) {
			cd.setAgency(dicAgencyMapper.getAllAgencyId());
		}

		List reportHouseResourceVOS = new ArrayList<ReportHouseResourceVO>();

		// 1.查询所有
		cd.getAgency().forEach(aLong -> {
			List<ReportHouseResourceVO> houseResourceReportlist = reportMapper.getHouseResourceReport(aLong, cd);

			if (!houseResourceReportlist.isEmpty()){
				houseResourceReportlist.forEach(reportHouseResourceVO -> {
					// 房源总面积
					reportHouseResourceVO.setTotalResourceArea(houseResourceMapper.getTotalResourceAreaByAgencyId(aLong));
					// 房源有证面积
					reportHouseResourceVO.setTotalResourceYzArea(houseResourceMapper.getTotalResourceYzAreaByAgencyId(aLong));
					// 房源无证面积
					reportHouseResourceVO.setTotalResourceWzArea(houseResourceMapper.getTotalResourceWzAreaByAgencyId(aLong));
					// 房源总个数
					reportHouseResourceVO.setTotalResourceNum(houseResourceMapper.getTotalResourceNumByAgencyId(aLong));

					reportHouseResourceVOS.add(reportHouseResourceVO);
				});
			}
		});

		if (reportHouseResourceVOS.isEmpty()) {
			log.error("未查询到房产信息");
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(), reportHouseResourceVOS);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), reportHouseResourceVOS);

	}

	public DTO getHouseAssetsReport(ReportCD cd) {
		log.info("获得房产统计报表");

		// 0.空就是查所有
		if (cd.getAgency().isEmpty()) {
			cd.setAgency(dicAgencyMapper.getAllAgencyId());
		}

		List reportHouseAssetsVOS = new ArrayList<ReportHouseAssetsVO>();
		// 1.查询所有
		cd.getAgency().forEach(aLong -> {
			ReportHouseAssetsVO houseAssetsReport = reportMapper.getHouseAssetsReport(aLong);
			if (houseAssetsReport.getAgencyName() != null) {
				reportHouseAssetsVOS.add(houseAssetsReport);
			}
		});

		if (reportHouseAssetsVOS.isEmpty()) {
			log.error("未查询到房产信息");
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(), reportHouseAssetsVOS);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), reportHouseAssetsVOS);
	}
}
