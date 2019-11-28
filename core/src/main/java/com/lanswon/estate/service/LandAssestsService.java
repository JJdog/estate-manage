package com.lanswon.estate.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.DatabaseConstants;
import com.lanswon.estate.bean.cd.LandAssetsCD;
import com.lanswon.estate.bean.pojo.LandAssets;
import com.lanswon.estate.bean.vo.LandAssetsVO;
import com.lanswon.estate.mapper.LandAssetsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 土地资产Service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class LandAssestsService {

	@Resource
	private LandAssetsMapper landAssetsMapper;


	public DTO insertLandAssets(LandAssets landAssets) {
		log.info("新增土地资产信息");
		if (landAssetsMapper.insert(landAssets) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return  new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO deleteLandAssets(String lid) {
		log.info("删除资产信息id为:{}的信息",lid);
		if (landAssetsMapper.deleteById(lid) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO updateLandAssets(LandAssets landAssets) {

		log.info("更新id为:{}的土地资产信息",landAssets.getId());
		if (landAssetsMapper.updateById(landAssets) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getLandAssetsInfo(int asc, LandAssetsCD cd) {

		log.info("获得第--{}--页共--{}--条数据",cd.getPage(),cd.getLimit());
		IPage<LandAssetsVO> landAssetsPageList = landAssetsMapper.getLandAssetsPageList(new Page(cd.getPage(),cd.getLimit()), asc,cd);
		if (landAssetsPageList.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),landAssetsPageList);
	}



	/**
	 * 通用的方法
	 * @param page 分页条件
	 * @param asc 顺序
	 * @param mapper 查询mapper
	 * @param <T> 范性
	 * @return dto
	 */
	private <T> DTO getAllDataWithPage(Page<T> page, int asc, BaseMapper mapper){
		Wrapper<T> wrapper;

		if (asc == 1){
			wrapper = new QueryWrapper<T>().orderByAsc(DatabaseConstants.BASE_CT);
		} else {
			wrapper = new QueryWrapper<T>().orderByDesc(DatabaseConstants.BASE_CT);
		}


		IPage<T> tPage = mapper.selectPage(page, wrapper);


		if (tPage.getSize() == 0){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),tPage);		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),tPage);
	}

	/**
	 * 分页查询返回
	 * @param pageList 结果
	 * @return dto
	 */
	private DTO rtnPageResult(IPage pageList){
		if(pageList.getSize() == 0){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),pageList);
	}

	public DTO getLandAssetsMenuInfo() {

		log.info("获得地产下拉菜单信息");

		List<LandAssets> landAssetsList = landAssetsMapper.selectList(Wrappers.<LandAssets>lambdaQuery()
				.select(LandAssets::getId, LandAssets::getLandNum));


		if (landAssetsList.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),landAssetsList);
	}
}
