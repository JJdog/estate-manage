package com.lanswon.estate.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.cd.WarnCD;
import com.lanswon.estate.bean.vo.SimpleWarnDealVO;
import com.lanswon.estate.bean.vo.SimpleWarnRentVO;
import com.lanswon.estate.mapper.DealMapper;
import com.lanswon.estate.mapper.RentChargeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 预警
 *
 * @author jaswine
 */
@Service
@Slf4j
public class WarnService {


	@Resource
	private DealMapper dealMapper;
	@Resource
	private RentChargeMapper rentChargeMapper;


	/**
	 * 合同预警
	 * @return
	 */
	public DTO getDealWarnPage(WarnCD cd) {
		log.info("获得预警合同信息");

		IPage<SimpleWarnDealVO> simpleWarnDeal = dealMapper.getSimpleWarnDeal(new Page<>(cd.getPage(),cd.getLimit()),cd.getAsc() , cd.getDay());

		if (simpleWarnDeal.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),simpleWarnDeal);
		}


		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),simpleWarnDeal);
	}

	/**
	 * 租金预警
	 * @return
	 */
	public DTO getRentWarnPage(WarnCD cd ) {
		log.info("获得预警合同信息");

		IPage<SimpleWarnRentVO> rentWarnPage = rentChargeMapper.getRentWarnPage(new Page<>(cd.getPage(),cd.getLimit()), cd.getAsc(),cd.getDay());

		if (rentWarnPage.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),rentWarnPage);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),rentWarnPage);

	}
}
