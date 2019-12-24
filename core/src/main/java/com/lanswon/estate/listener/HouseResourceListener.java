package com.lanswon.estate.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.lanswon.estate.bean.po.PoiHouseResourcePO;
import com.lanswon.estate.mapper.poi.PoiHouseResourceMapper;
import com.lanswon.estate.service.IoService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class HouseResourceListener extends AnalysisEventListener<PoiHouseResourcePO> {

	private IoService service;

	private PoiHouseResourceMapper mapper;

	public HouseResourceListener(IoService ioService, PoiHouseResourceMapper poiHouseResourceMapper) {
		this.service = ioService;
		this.mapper = poiHouseResourceMapper;
	}

	@Override
	public void invoke(PoiHouseResourcePO houseResource, AnalysisContext analysisContext) {
		log.info("解析到一条数据:{}", JSON.toJSONString(houseResource));

		if (houseResource.getRentStatusValue() != null){
			switch (houseResource.getRentStatusValue()){
				case "闲置":
					houseResource.setRentStatus(1);
					break;
				case "已出租":
					houseResource.setRentStatus(2);
					break;
				default:
					throw new RuntimeException("不合法的出租类型");
			}
		}

		if (houseResource.getSellStatusValue() != null){
			switch (houseResource.getSellStatusValue()){
				case "未出售":
					houseResource.setSellStatus(1);
					break;
				case "出售未过户":
					houseResource.setSellStatus(2);
					break;
				case "出售已过户":
					houseResource.setSellStatus(3);
					break;
				default:
					throw new RuntimeException("不合法的出售类型");

			}
		}


		houseResource.setResourceArea(houseResource.getYzArea() + houseResource.getWzArea());
		houseResource.setCreatedTime(new Date());
		mapper.insert(service.convertHouseResource2Database(houseResource));
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}
}
