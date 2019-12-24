package com.lanswon.estate.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lanswon.estate.bean.po.PoiHouseAssetsPO;
import com.lanswon.estate.mapper.poi.PoiHouseAssetsMapper;
import com.lanswon.estate.service.IoService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
public class HouseAssetsListener extends AnalysisEventListener<PoiHouseAssetsPO> {

	private IoService service;
	private PoiHouseAssetsMapper mapper;

	public HouseAssetsListener(IoService service,PoiHouseAssetsMapper mapper){
		this.service = service;
		this.mapper = mapper;
	}

	/**
	 * 批处理阈值
	 */
	private static final int BATCH_COUNT = 20;
	private final List<PoiHouseAssetsPO> list = new ArrayList<>(BATCH_COUNT);


	@Override
	public void invoke(PoiHouseAssetsPO houseAssets, AnalysisContext analysisContext) {

		log.info("解析到一条数据:{}", JSON.toJSONString(houseAssets));
		houseAssets.setCreatedTime(new Date());
		mapper.insert(service.convertHouseAssets2Database(houseAssets));
		if (list.size() >= BATCH_COUNT) {
			list.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("统计");
	}



}
