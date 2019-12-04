package com.lanswon.estate.service;


import com.lanswon.commons.core.poi.FileIOUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.po.PoiHouseAssetsPO;
import com.lanswon.estate.bean.po.PoiHouseResourcePO;
import com.lanswon.estate.bean.po.PoiLandAssetsPO;
import com.lanswon.estate.bean.po.PoiTransFlow;
import com.lanswon.estate.bean.vo.PoiResultVO;
import com.lanswon.estate.mapper.DicAgencyMapper;
import com.lanswon.estate.mapper.DicAssetsCoMapper;
import com.lanswon.estate.mapper.HouseAssetsMapper;
import com.lanswon.estate.mapper.PoiLandAssetsMapper;
import com.lanswon.estate.mapper.poi.PoiHouseAssetsMapper;
import com.lanswon.estate.mapper.poi.PoiHouseResourceMapper;
import com.lanswon.estate.mapper.poi.PoiTransFlowMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 文件IO
 *
 * @author jaswine
 */
@Service
@Slf4j
public class IoService {


	@Resource
	private DicAgencyMapper agencyMapper;
	@Resource
	private HouseAssetsMapper houseAssetsMapper;
	@Resource
	private DicAssetsCoMapper dicAssetsCoMapper;
	@Resource
	private PoiLandAssetsMapper poiLandAssetsMapper;
	@Resource
	private PoiHouseResourceMapper poiHouseResourceMapper;
	@Resource
	private PoiHouseAssetsMapper poiHouseAssetsMapper;
	@Resource
	private PoiTransFlowMapper poiTransFlowMapper;


	/** 管理机构名称-id对应关系 */
	private List<String> agencyNameAndId;
	/** 房产名称-id对应关系 */
	private List<String> houseAssetsNameAndId;
	private List<String> ownerNameAndId;


	private void init(){
		log.info("初始化转换数据");
		agencyNameAndId = agencyMapper.getAllNameAndId();
		houseAssetsNameAndId = houseAssetsMapper.getAllSerialId();
		ownerNameAndId = dicAssetsCoMapper.getAllNameAndId();
	}


	/** In 地产*/
	public DTO importLandAssets(MultipartFile file) {
		init();

		/* 写入数据库对象 */
		List<PoiLandAssetsPO> dbLandAssets = new ArrayList<>();

		/* 汇总数据 */
		PoiResultVO poiResultVO = new PoiResultVO();

		try {
			// 获得Excel数据
			List<PoiLandAssetsPO> landAssetsPOS = FileIOUtil.importFile(file.getInputStream(), 0, 1, PoiLandAssetsPO.class);

			poiResultVO.setTotalRow(landAssetsPOS.size());


			// 转码后放到新容器
			landAssetsPOS.forEach(poiLandAssetsPO -> {
				dbLandAssets.add(convertLandAssets2Database(poiLandAssetsPO));
			});

			// 遍历容器插入
			dbLandAssets.forEach(poiLandAssetsPO -> poiLandAssetsMapper.insert(poiLandAssetsPO));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),poiResultVO);
	}

	/** In房产 */
	public DTO importHouseAssets(MultipartFile file) {
		init();

		List<PoiHouseAssetsPO> dbHouseAssets = new ArrayList<>();


		try {
			List<PoiHouseAssetsPO> poiHouseAssetsPOS = FileIOUtil.importFile(file.getInputStream(), 0, 1, PoiHouseAssetsPO.class);

			poiHouseAssetsPOS.forEach(poiHouseAssetsPO -> dbHouseAssets.add(convertHouseAssets2Database(poiHouseAssetsPO)));

			dbHouseAssets.forEach(poiHouseAssetsPO -> poiHouseAssetsMapper.insert(poiHouseAssetsPO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	/** In房源 */
	public DTO importHouseResource(MultipartFile file) {

		init();

		/* 写入数据库对象 */
		List<PoiHouseResourcePO> dbHouseResource = new ArrayList<>();

		PoiResultVO poiResultVO = new PoiResultVO();

		try {
			// 获得Excel数据
			List<PoiHouseResourcePO> houseResources = FileIOUtil.importFile(file.getInputStream(), 0, 1, PoiHouseResourcePO.class);

			poiResultVO.setTotalRow(houseResources.size());

			// 转码后放到新容器
			houseResources.forEach(poiHouseResourcePO ->{
				if (!dbHouseResource.add(convertHouseResource2Database(poiHouseResourcePO))){
					log.error(poiHouseResourcePO.getHouseSerial()+"失败");
					poiResultVO.getErrorRows().add(poiHouseResourcePO.getHouseSerial());
				}
			});

			// 遍历容器插入
			dbHouseResource.forEach(poiHouseResourcePO -> poiHouseResourceMapper.insert(poiHouseResourcePO));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),poiResultVO);
	}



	/** In流水 */
	public DTO importTransFlow(MultipartFile file) {
		try {
			List<PoiTransFlow> poiTransFlows = FileIOUtil.importFile(file.getInputStream(), 0, 1, PoiTransFlow.class);

			poiTransFlows.forEach(poiTransFlow -> {
				if (poiTransFlow.getFkMustMoneyId() == null){
					// todo 记录
					log.error("流水错误-->{}",poiTransFlow.getMustMoneyNum());
				}
				poiTransFlowMapper.insert(poiTransFlow);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Workbook exportTransFlow(Date date){

		List<PoiTransFlow> poiTransFlows = poiTransFlowMapper.getRealMoney(date);
		return FileIOUtil.exportFile("交易流水", PoiTransFlow.class, poiTransFlows);
	}


	/** 土产转码 */
	private PoiLandAssetsPO convertLandAssets2Database(PoiLandAssetsPO poiLandAssetsPO){
		// 产权人
		poiLandAssetsPO.setFkOwnId(ownerName2Id(poiLandAssetsPO.getOwner()));
		// 管理单位
		poiLandAssetsPO.setFkAgencyId(agencyName2Id(poiLandAssetsPO.getAgencyName()));
		return poiLandAssetsPO;
	}

	/**
	 * 房源转码
	 * @param poiHouseResourcePO 房源对象
	 * @return 转码后对象
	 */
	private PoiHouseResourcePO convertHouseResource2Database(PoiHouseResourcePO poiHouseResourcePO){

		// 管理机构转码
		poiHouseResourcePO.setFkAgencyId(agencyName2Id(poiHouseResourcePO.getAgencyName()));
		// 房产转码
		poiHouseResourcePO.setFkHouseAssetsId(houseAssetsSerialName2Id(poiHouseResourcePO.getHouseSerial()));
		return poiHouseResourcePO;
	}


	/**
	 * 房产转码
	 * @param poiHouseAssetsPO 房产对象
	 * @return 转码后对象
	 */
	private PoiHouseAssetsPO convertHouseAssets2Database(PoiHouseAssetsPO poiHouseAssetsPO){
		// 管理单位
		poiHouseAssetsPO.setFkAgencyId(agencyName2Id(poiHouseAssetsPO.getAgencyName()));
		// 产权拥有者
		poiHouseAssetsPO.setFkOwnId(ownerName2Id(poiHouseAssetsPO.getOwner()));

		return poiHouseAssetsPO;
	}



	/*============名称转id==========================*/




	/** 管理单位转码 */
	private Long agencyName2Id(String name){
		for (String s : agencyNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}

		return null;
	}

	/** 房产转码 */
	private Long houseAssetsSerialName2Id(String name){
		for (String s: houseAssetsNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 产权所有人转码 */
	private Long ownerName2Id(String name){
		for (String s : ownerNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}



}
