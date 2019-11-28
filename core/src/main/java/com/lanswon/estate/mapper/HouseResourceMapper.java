package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.HouseResourceCD;
import com.lanswon.estate.bean.pojo.HouseResource;
import com.lanswon.estate.bean.vo.DetailHouseResourceVO;
import com.lanswon.estate.bean.vo.HouseResourceMenuVO;
import com.lanswon.estate.bean.vo.SimpleHouseResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 房源信息Mapper
 * @author jaswine
 */
@Repository
@Mapper
public interface HouseResourceMapper extends BaseMapper<HouseResource> {


	IPage<SimpleHouseResourceVO> getHouseResourcePageList(@Param(value = "page") Page<Object> objectPage,
	                                                      @Param(value = "asc") int asc,
	                                                      @Param(value = "cd") HouseResourceCD cd);

	DetailHouseResourceVO getHouseResourceDetailInfo(long hid);

	@Select("SELECT  t.real_rent_charge FROM house_resource t WHERE t.id = #{id}")
	double getHouseMonthRentByResourceId(long id);

	@Update("UPDATE house_resource t SET t.rent_status = 1 WHERE t.id = #{id}")
	boolean updateResource2HasRented(long id);

	@Update("UPDATE house_resource t SET t.rent_status = 0 WHERE t.id = #{id} ")
	boolean updateResource2FreeRent(long id);

	List<HouseResourceMenuVO> getNoRentHouseResource();

	@Select("SELECT count(id) FROM house_resource WHERE fk_agency_id = #{id} ")
	int getTotalResourceNumByAgencyId(Long id);

	@Select("SELECT sum(resource_area) FROM house_resource WHERE fk_agency_id = #{id} ")
	double getTotalResourceAreaByAgencyId(Long id);

	@Select("SELECT sum(resource_yz_area) FROM house_resource WHERE fk_agency_id = #{id} ")
	double getTotalResourceYzAreaByAgencyId(Long id);

	@Select("SELECT sum(resource_wz_area) FROM house_resource WHERE fk_agency_id = #{id} ")
	double getTotalResourceWzAreaByAgencyId(Long id);
}
