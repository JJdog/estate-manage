package com.lanswon.estate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.cd.HouseAssetsCD;
import com.lanswon.estate.bean.pojo.HouseAssets;
import com.lanswon.estate.bean.vo.HouseAssetsPageVO;
import com.lanswon.estate.mapper.HouseAssetsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 房产Service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class HouseAssetsService {

	@Resource
	private HouseAssetsMapper houseAssetsMapper;


	public DTO insertHouseAssets(HouseAssets houseAssets) {
		log.info("新增房屋资产信息");
		houseAssets.setCreatedTime(new Date());

		if (houseAssetsMapper.insert(houseAssets) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return  new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	// todo 和房产关联的房源信息
	public DTO deleteHouseAssets(String lid) {
		log.info("删除资产信息id为:{}的信息",lid);
		if (houseAssetsMapper.deleteById(lid) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO updateHouseAssets(HouseAssets houseAssets) {

		log.info("更新id为:{}的房屋资产信息",houseAssets.getId());
		if (houseAssetsMapper.updateById(houseAssets) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getHouseAssetsPage(HouseAssetsCD cd) {

		log.info("获得第--{}--页共--{}--条数据",cd.getPage(),cd.getLimit());
		Page<HouseAssets> page = new Page<>(cd.getPage(), cd.getLimit());
		IPage<HouseAssetsPageVO> houseAssetsListPage = houseAssetsMapper.getHouseAssetsPage(page,cd);

		if(houseAssetsListPage.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),houseAssetsListPage);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),houseAssetsListPage);

	}

	public DTO getHouseAssetsInfoWithoutPage() {
		log.info("获得房产下拉菜单信息");

		List<HouseAssets> houseAssetsList = houseAssetsMapper.selectList(Wrappers.<HouseAssets>lambdaQuery()
				.select(HouseAssets::getId, HouseAssets::getAssetsName));


		if (houseAssetsList.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),houseAssetsList);

	}

}
