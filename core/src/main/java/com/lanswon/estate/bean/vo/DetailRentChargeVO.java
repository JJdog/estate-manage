package com.lanswon.estate.bean.vo;


import lombok.Data;

import java.util.List;

/**
 * 租金详细信息
 *
 * @author jaswine
 */
@Data
public class DetailRentChargeVO {


	/** 合同详情 */
	public DetailDealVO detailDeal;

	/** 月租金详情 */
	private List<MonthRentChargeVO> monthRentCharge;


}
