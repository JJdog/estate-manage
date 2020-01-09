package com.lanswon.estate.service;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.pojo.MoneyDepositReturn;
import com.lanswon.estate.mapper.MoneyDepositReturnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 保证金Service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class DepositService {

	@Resource
	private MoneyDepositReturnMapper moneyDepositReturnMapper;



	public DTO returnDeposit(MoneyDepositReturn depositReturn) {
		log.info("新增保证金退还记录");

		if (moneyDepositReturnMapper.insert(depositReturn) == 0){
			log.error("新增保证金退还记录 失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}
}
