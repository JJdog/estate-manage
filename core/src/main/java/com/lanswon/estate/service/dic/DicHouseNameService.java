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
import com.lanswon.estate.bean.DatabaseConstants;
import com.lanswon.estate.bean.cd.SimpleCD;
import com.lanswon.estate.bean.pojo.dic.DicHouseName;
import com.lanswon.estate.mapper.DicHouseNameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class DicHouseNameService {


	@Resource
	private DicHouseNameMapper dicHouseNameMapper;


	public DTO insertHouseName(DicHouseName houseName) {
		log.info("新增共有类型数据");
		if (dicHouseNameMapper.insert(houseName) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());

	}

	@Transactional(rollbackFor = Exception.class)
	public DTO deleteHouseName(String id) {
		log.info("删除id为-->{}的共有情况数据",id);
		// 删除 共有情况
		if (dicHouseNameMapper.deleteById(id) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}


		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO updateHouseName(DicHouseName houseName) {
		log.info("更新共有为-->{}的共有情况,",houseName.getId());

		if (dicHouseNameMapper.updateById(houseName) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}


	public DTO getAllHouseNameInfo(SimpleCD cd) {
		log.info("获得所有共有情况信息");
		Page<DicHouseName> page = new Page<>(cd.getPage(), cd.getLimit());
		return getAllDataWithPage(page, cd.getAsc(), dicHouseNameMapper);

	}

	public DTO getAllHouseNameWithoutPage() {
		log.info("获得所有共有情况信息-- 不分页");

		return getAllDataWithoutPage(dicHouseNameMapper);

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
