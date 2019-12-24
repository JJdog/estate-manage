package com.lanswon.estate.service;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.lanswon.commons.core.poi.FileIOUtil;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.po.PoiHouseAssetsPO;
import com.lanswon.estate.bean.po.PoiHouseResourcePO;
import com.lanswon.estate.bean.po.PoiLandAssetsPO;
import com.lanswon.estate.bean.po.PoiTransFlow;
import com.lanswon.estate.bean.vo.PoiResultVO;
import com.lanswon.estate.listener.HouseAssetsListener;
import com.lanswon.estate.listener.HouseResourceListener;
import com.lanswon.estate.mapper.*;
import com.lanswon.estate.mapper.poi.PoiHouseAssetsMapper;
import com.lanswon.estate.mapper.poi.PoiHouseResourceMapper;
import com.lanswon.estate.mapper.poi.PoiTransFlowMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
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
	private DicLandUsageMapper dicLandUsageMapper;
	@Resource
	private DicLandNatureMapper dicLandNatureMapper;
	@Resource
	private DicHouseUsageMapper dicHouseUsageMapper;
	@Resource
	private DicHouseNatureMapper dicHouseNatureMapper;
	@Resource
	private DicResourceTypeMapper dicResourceTypeMapper;
	@Resource
	private DicHouseNameMapper dicHouseNameMapper;
	@Resource
	private LandAssetsMapper landAssetsMapper;
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
	/** 房产用途-id对应关系 */
	private List<String> houseUsageAndId;
	/** 房产性质-id对应关系 */
	private List<String> houseNatureAndId;
	/** 产权所有人-id对应关系 */
	private List<String> ownerNameAndId;
	/** 土地证号-id对应关系 */
	private List<String> landNoAndId;
	/** 土地用途-id对应关系 */
	private List<String> landUsageAndId;
	/** 土地性质-id对应关系 */
	private List<String> landNatureAndId;
	/** 房产名称-id对应关系 */
	private List<String> houseNameAndId;
	/** 房源类型-id对应关系 */
	private List<String> resourceTypeAndId;

	private void init(){
		log.info("初始化转换数据");
		agencyNameAndId = agencyMapper.getAllNameAndId();
		houseAssetsNameAndId = houseAssetsMapper.getAllSerialId();
		houseUsageAndId = dicHouseUsageMapper.getAllUsageAndId();
		houseNatureAndId = dicHouseNatureMapper.getAllNatureAndId();
		ownerNameAndId = dicAssetsCoMapper.getAllNameAndId();
		houseNameAndId = dicHouseNameMapper.getAllNameAndId();
		landNoAndId = landAssetsMapper.getAllLandNoAndId();
		landUsageAndId = dicLandUsageMapper.getAllUsageAndId();
		landNatureAndId = dicLandNatureMapper.getAllNatureAndId();
		resourceTypeAndId = dicResourceTypeMapper.getAllResourceTypeAndId();
	}


	/** In 地产*/
	@Transactional(rollbackFor = Exception.class)
	public DTO importLandAssets(MultipartFile file) {
		log.info("初始化转换信息");
		init();

		/* 写入数据库对象 */
		List<PoiLandAssetsPO> dbLandAssets = new ArrayList<>();

		/* 汇总数据 */
		PoiResultVO poiResultVO = new PoiResultVO();
		poiResultVO.setPoiName("导入土地产证信息"+ DateTimeUtil.getDateTime());

		try {
			// 获得Excel数据
			@Valid
			List<PoiLandAssetsPO> landAssetsList = FileIOUtil.importFile(file.getInputStream(), 0, 1, PoiLandAssetsPO.class);
			log.info("获得Excel数据,总数为:{}",landAssetsList.size());

			// 导入总条数
			poiResultVO.setTotalRow(landAssetsList.size());
			List<String> errorList = new ArrayList<>();

			// 转码后放到新容器
			landAssetsList.forEach(poiLandAssets -> {


				// 转码
				PoiLandAssetsPO landAssetsPO = convertLandAssets2Database(poiLandAssets);

				// 转码失败
				if (landAssetsPO.getFkAgencyId() == null || landAssetsPO.getFkOwnId() == null ){
					poiResultVO.setErrorRow(poiResultVO.getErrorRow() + 1);
					errorList.add(poiLandAssets.getLandNo());
					return;
				}
				// 转码成功的对象
				dbLandAssets.add(landAssetsPO);
			});

			// 错误信息
			poiResultVO.setErrorRows(errorList);
			poiResultVO.setSuccessRow(poiResultVO.getTotalRow() - poiResultVO.getErrorRow());

			// 遍历容器插入
			dbLandAssets.forEach(poiLandAssets -> {
				poiLandAssets.setCreatedTime(new Date());
				poiLandAssets.setUpdatedTime(null);
				poiLandAssetsMapper.insert(poiLandAssets);
			});
		} catch (Exception e) {
			// 错误信息
			poiResultVO.setErrorRow(poiResultVO.getTotalRow());
			poiResultVO.setSuccessRow(poiResultVO.getTotalRow() - poiResultVO.getErrorRow());

			e.printStackTrace();
		}

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),poiResultVO);
	}

	/** In房产 */
	@Transactional(rollbackFor = Exception.class)
	public DTO importHouseAssets(MultipartFile file) {
		log.info("初始化转码信息");
		init();
		/* 汇总数据 */
		PoiResultVO poiResultVO = new PoiResultVO();
		poiResultVO.setPoiName("导入房屋产证信息"+ DateTimeUtil.getDateTime());

		try {
			EasyExcel.read(file.getInputStream(), PoiHouseAssetsPO.class, new HouseAssetsListener(this,this.poiHouseAssetsMapper)).sheet().doRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),poiResultVO);

	}


	/** In房源 */
	@Transactional(rollbackFor = Exception.class)
	public DTO importHouseResource(MultipartFile file) throws IOException {
		log.info("初始化转换信息");
		init();

		/* 写入数据库对象 */
		List<PoiHouseResourcePO> dbResource = new ArrayList<>();

		/* 汇总数据 */
		PoiResultVO poiResultVO = new PoiResultVO();
		poiResultVO.setPoiName("导入房源信息"+ DateTimeUtil.getDateTime());

		// 获得Excel数据
		EasyExcel.read(file.getInputStream(), PoiHouseResourcePO.class, new HouseResourceListener(this,this.poiHouseResourceMapper)).sheet().doRead();


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
		log.info(poiLandAssetsPO.getLandNo(),"{}转码");
		// 产权人
		poiLandAssetsPO.setFkOwnId(ownerName2Id(poiLandAssetsPO.getOwner()));
		// 管理单位
		poiLandAssetsPO.setFkAgencyId(agencyName2Id(poiLandAssetsPO.getAgencyName()));
		if (poiLandAssetsPO.getLandUsage() != null){
			// 土产用途
			poiLandAssetsPO.setFkLandUsageId(landUsage2Id(poiLandAssetsPO.getLandUsage()));
		}
		if (poiLandAssetsPO.getLandNature() != null){
			// 土产类型
			poiLandAssetsPO.setFkLandNatureId(landNature2Id(poiLandAssetsPO.getLandNature()));
		}

		return poiLandAssetsPO;
	}

	/** 房产转码 */
	public PoiHouseAssetsPO convertHouseAssets2Database(PoiHouseAssetsPO poiHouseAssetsPO){
		// 管理单位
		poiHouseAssetsPO.setFkAgencyId(agencyName2Id(poiHouseAssetsPO.getAgencyName()));
		// 产权拥有者
		poiHouseAssetsPO.setFkOwnId(ownerName2Id(poiHouseAssetsPO.getOwner()));
		// 土地证号
		poiHouseAssetsPO.setFkLandAssetsId(landNo2Id(poiHouseAssetsPO.getLandNo()));
		// 房产名称
		poiHouseAssetsPO.setFkHouseNameId(houseName2Id(poiHouseAssetsPO.getAssetsName()));

		if (poiHouseAssetsPO.getHouseUsage() != null){
			// 房产用途
			poiHouseAssetsPO.setFkHouseUsage(houseUsage2Id(poiHouseAssetsPO.getHouseUsage()));
		}

		if (poiHouseAssetsPO.getHouseNature() != null){
			// 房产性质
			poiHouseAssetsPO.setFkHouseNature(houseNature2Id(poiHouseAssetsPO.getHouseNature()));
		}

		return poiHouseAssetsPO;
	}


	/**
	 * 房源转码
	 * @param poiHouseResourcePO 房源对象
	 * @return 转码后对象
	 */
	public PoiHouseResourcePO convertHouseResource2Database(PoiHouseResourcePO poiHouseResourcePO){

		// 管理机构-id转码
		poiHouseResourcePO.setFkAgencyId(agencyName2Id(poiHouseResourcePO.getAgencyName()));
		// 房产证-id转码
		poiHouseResourcePO.setFkHouseAssetsId(houseAssetsSerialName2Id(poiHouseResourcePO.getHouseSerial()));

		if (poiHouseResourcePO.getSellStatus() == 1){
			// 房源类型-id转码
			poiHouseResourcePO.setFkResourceTypeId(resourceType2Id(poiHouseResourcePO.getResourceType()));
		}

		return poiHouseResourcePO;
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


	/** 产权所有人转码 */
	private Long ownerName2Id(String name){
		for (String s : ownerNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 土地证号转码转码 */
	private Long landNo2Id(String name){
		for (String s : landNoAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 土地证号转码转码 */
	private Long landUsage2Id(String name){
		for (String s : landUsageAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 土地证号转码转码 */
	private Long landNature2Id(String name){
		for (String s : landNatureAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}


	/** 房产名称转码 */
	private Long houseName2Id(String name){
		for (String s : houseNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}


	/** 房产证号转码 */
	private Long houseAssetsSerialName2Id(String name){
		for (String s: houseAssetsNameAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 房产用途转码 */
	private Long houseUsage2Id(String name){
		for (String s : houseUsageAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 房产性质转码 */
	private Long houseNature2Id(String name){
		for (String s : houseNatureAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

	/** 房源类型转码 */
	private Long resourceType2Id(String name){
		for (String s : resourceTypeAndId){
			if (s.startsWith(name)){
				return Long.valueOf(StringUtils.substringAfter(s, "$"));
			}
		}
		return null;
	}

}
