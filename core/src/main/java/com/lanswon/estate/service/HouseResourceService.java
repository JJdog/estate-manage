package com.lanswon.estate.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.cd.HouseResourceCD;
import com.lanswon.estate.bean.pojo.HouseResource;
import com.lanswon.estate.bean.vo.DetailHouseResourceVO;
import com.lanswon.estate.bean.vo.HouseResourceMenuVO;
import com.lanswon.estate.bean.vo.page.HouseResourcePageVO;
import com.lanswon.estate.mapper.HouseResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 房源Service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class HouseResourceService {

	@Resource
	private HouseResourceMapper houseResourceMapper;


	/**
	 * 新增房源信息
	 * @param houseResource 房源信息
	 * @return DTO
	 */
	public DTO insertHouseResource(HouseResource houseResource) {
		log.info("新增房源信息");
		/* 创建时间 */
		houseResource.setCreatedTime(new Date());
		/* 面积 */
		houseResource.setResourceArea(houseResource.getYzArea() + houseResource.getWzArea());
		//houseResource.setRentMoneyPerArea(Double.parseDouble(new DecimalFormat("#.##").format(houseResource.getRealRentCharge()/houseResource.getResourceArea())));
		if (houseResourceMapper.insert(houseResource) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO deleteHouseResource(long hid) {

		log.info("删除id为-->{}的房源信息",hid);
		// todo 租售房源不可删除
		if (houseResourceMapper.selectById(String.valueOf(hid)) == null){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		if (houseResourceMapper.deleteById(String.valueOf(hid)) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO updateHouseResource(HouseResource houseResource) {
		log.info("更新房源id为-->{}的房源信息",houseResource.getId());
		if (houseResourceMapper.updateById(houseResource) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}


	public DTO getHouseResourcePage(HouseResourceCD cd) {

		log.info("获得所有房源信息,第{}页,共{}条", cd.getPage(), cd.getLimit());

		IPage<HouseResourcePageVO> houseResourcePageList = houseResourceMapper.getHouseResourcePage(new Page<>(cd.getPage(), cd.getLimit()),cd);

		return rtnPageResult(houseResourcePageList);
	}

	public DTO getHouseResourceDetail(long hid) {
		log.info("获得房源id为:{}详细信息",hid);

		DetailHouseResourceVO houseResourceDetailInfo = houseResourceMapper.getHouseResourceDetailInfo(hid);

		if (houseResourceDetailInfo == null){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),houseResourceDetailInfo);

	}




	public DTO getHouseResourceMenuInfo() {


		log.info("获得地产下拉菜单信息");

		List<HouseResource> landAssetsList = houseResourceMapper.selectList(Wrappers.<HouseResource>lambdaQuery()
				.select(HouseResource::getId,HouseResource::getBuildRoom));


		if (landAssetsList.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),landAssetsList);
	}

	public DTO getNoRentHouseResource() {
		log.info("获得未租赁的房源信息");

		List<HouseResourceMenuVO> resource = houseResourceMapper.getNoRentHouseResource();

		if (resource.isEmpty()){
			log.error("没有房源可租");
			return new SimpleRtnDTO(500,"没有房源可租");
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),resource);

	}


	/**
	 * 分页查询返回
	 * @param pageList 结果
	 * @return dto
	 */
	private DTO rtnPageResult(IPage pageList){

		if(pageList.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),pageList);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),pageList);
	}
}
