package com.lanswon.estate.service.dic;


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
import com.lanswon.estate.bean.pojo.dic.DicAgency;
import com.lanswon.estate.mapper.DicAgencyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DicAgencyService {


	@Resource
	private DicAgencyMapper dicAgencyMapper;

	public DTO insertAgency(DicAgency co) {
		log.info("新增共有类型数据");
		if (dicAgencyMapper.insert(co) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());

	}
	@Transactional(rollbackFor = Exception.class)
	public DTO deleteAgency(String id) {
		log.info("删除id为-->{}的共有情况数据",id);
		// 删除 共有情况
		if (dicAgencyMapper.deleteById(id) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}


		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}
	public DTO updateAgency(DicAgency co) {
		log.info("更新共有为-->{}的共有情况",co.getId());

		co.setUpdatedTime(new Date());
		if (dicAgencyMapper.updateById(co) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getAllAgencyInfo(Page<DicAgency> page, int asc) {
		log.info("获得所有共有情况信息");

		return getAllDataWithPage(page,asc,dicAgencyMapper);
	}
	public DTO getAllAgencyWithoutPage() {
		log.info("获得所有共有情况信息");

		return getAllDataWithoutPage(dicAgencyMapper);
	}


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
