package com.lanswon.estate.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.constant.DatabaseConstants;
import com.lanswon.estate.bean.pojo.Renter;
import com.lanswon.estate.mapper.RenterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RenterService {


	@Resource
	private RenterMapper renterMapper;


	/**
	 * 新增共有类型情况
	 * @param renter 共有类型
	 * @return dto
	 */
	public DTO insertRenter(Renter renter) {
		log.info("新增共有类型数据");
		renter.setCreatedTime(new Date());
		if (renterMapper.insert(renter) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());

	}

	/**
	 * 删除共有情况
	 * @param id 共有类型id
	 * @return dto
	 */
	@Transactional(rollbackFor = Exception.class)
	public DTO deleteRenter(String id) {
		log.info("删除id为-->{}的共有情况数据",id);
		// 删除 共有情况
		if (renterMapper.deleteById(id) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}


		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	/**
	 * 更新共有情况
	 * @param renter 共有情况
	 * @return dto
	 */
	public DTO updateRenter(Renter renter) {
		log.info("更新共有为-->{}的共有情况,",renter.getId());

		if (renterMapper.updateById(renter) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}



	/**
	 * 获得所有共有情况信息
	 * @return dto
	 */
	public DTO getAllRenterInfo(Page<Renter> page, int asc) {
		log.info("获得所有共有情况信息");

		return getAllDataWithPage(page,asc,renterMapper);
	}


	/**
	 * 获得所有共有情况信息
	 * @return dto
	 */
	public DTO getAllRenterWithoutPage() {
		log.info("获得所有共有情况信息");

		return getAllDataWithoutPage(renterMapper);
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
	 * 不分页获得所有
	 * @param mapper 查询对象
	 * @param <T> 结果泛型
	 * @return dto
	 */
	private <T> DTO getAllDataWithoutPage(BaseMapper<T> mapper){

		List<T> list = mapper.selectList(null);

		if (list.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),list);
		}

		log.info(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),list);
	}

}
