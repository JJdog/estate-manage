package com.lanswon.estate.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.estate.bean.po.PoiDealPO;
import com.lanswon.estate.constant.PojoConstants;
import com.lanswon.estate.mapper.PoiDealMapper;
import com.lanswon.estate.service.IoService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class DealListener extends AnalysisEventListener<PoiDealPO> {

	private IoService service;

	private PoiDealMapper mapper;

	public DealListener(IoService ioService, PoiDealMapper poiDealMapper) {
		this.service = ioService;
		this.mapper = poiDealMapper;
	}

	@Override
	public void invoke(PoiDealPO dealPO, AnalysisContext analysisContext) {
		log.info("解析到一条数据:{}", JSON.toJSONString(dealPO));

		if (dealPO.getDealTypeValue() != null){
			switch (dealPO.getDealTypeValue()){
				case "协商出租":
					dealPO.setDealType(PojoConstants.DEAL_TYPE_XS);
					break;
				case "一事一议":
					dealPO.setDealType(PojoConstants.DEAL_TYPE_YS);
					break;
				case "挂靠出租":
					dealPO.setDealType(PojoConstants.DEAL_TYPE_GK);
					break;
				default:
					throw new RuntimeException("不合法的合同类型");
			}
		}

		if (dealPO.getPayTypeValue() != null){
			switch (dealPO.getPayTypeValue()){
				case "以十二个月为一周期支付":
					dealPO.setPayType(12);
					break;
				case "以六个月为一周期支付":
					dealPO.setPayType(6);
					break;
				case "以三个月为一周期支付":
					dealPO.setPayType(3);
					break;
				case "以一个月为一周期支付":
					dealPO.setPayType(1);
					break;
				default:
					throw new RuntimeException("不合法的支付类型");
			}
		}

		if (dealPO.getIsNewRentValue() != null){
			switch (dealPO.getIsNewRentValue()){
				case "新签":
					dealPO.setIsNewRent(PojoConstants.DEAL_NEW_RENT);
					break;
				case "续签":
					dealPO.setIsNewRent(PojoConstants.DEAL_OLD_RENT);
					break;
				default:
					throw new RuntimeException("不合法的签订类型");
			}
		}

		if (dealPO.getIsHaveDiscountValue() != null){
			switch (dealPO.getIsHaveDiscountValue()){
				case "有":
					dealPO.setIsHaveDiscount(PojoConstants.DEAL_YES_DICOUNT);
					break;
				case "无":
					dealPO.setIsHaveDiscount(PojoConstants.DEAL_NO_DICOUNT);
					break;
				default:
					throw new RuntimeException("不合法的有无优惠类型");
			}
		}

		dealPO.setEndTime(DateTimeUtil.addMonth(dealPO.getStartTime(),dealPO.getRentMonth()).getTime());
		dealPO.setCreatedTime(new Date());
		dealPO.setDealReviewStatus(PojoConstants.DEAL_REVIEW_PASS);
		dealPO.setDealExistStatus(PojoConstants.DEAL_RUN_NORMAL);

		mapper.insert(service.convertDeal2Database(dealPO));
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}
}
