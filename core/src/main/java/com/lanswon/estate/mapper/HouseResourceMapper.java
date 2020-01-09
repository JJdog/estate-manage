package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.HouseResourceCD;
import com.lanswon.estate.bean.pojo.HouseResource;
import com.lanswon.estate.bean.pojo.dic.DicAgency;
import com.lanswon.estate.bean.pojo.dic.DicHouseName;
import com.lanswon.estate.bean.vo.DetailHouseResourceVO;
import com.lanswon.estate.bean.vo.HouseResourceMenuVO;
import com.lanswon.estate.bean.vo.dmenu.HouseResourceDmenu;
import com.lanswon.estate.bean.vo.page.HouseResourcePageVO;
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


	IPage<HouseResourcePageVO> getHouseResourcePage(@Param(value = "page") Page<Object> objectPage,
	                                                @Param(value = "cd") HouseResourceCD cd);

	DetailHouseResourceVO getHouseResourceDetailInfo(long hid);

	@Update("UPDATE house_resource t SET t.rent_status = #{status} WHERE t.id = #{rid} ")
	boolean updateResourceRentStatus(@Param("rid")Long rid,
	                                 @Param("status")Integer status);

	/** 房源已出租 */
	@Update("UPDATE house_resource t SET t.rent_status = #{rent}  WHERE t.id = #{id}")
	boolean updateResource2HasRented(@Param("id") long id,
	                                 @Param("rent") int rent);
	/** 房源未出租 */
	@Update("UPDATE house_resource t SET t.rent_status = #{rent} WHERE t.id = #{id} ")
	boolean updateResource2FreeRent(@Param("id") long id,
	                                @Param("rent") int rent);

	/** 未出租的房源 */
	List<HouseResourceMenuVO> getNoRentHouseResource();



	List<DetailHouseResourceVO> getHouseResourceDetailByDealId(long id);

	@Select("SELECT resource_area FROM house_resource t WHERE t.id = #{id} ")
	Double getHouseResourceAreaById(Long id);


	List<DicAgency> getAgencyNameAndIdById(Long aid);

	List<DicHouseName> getParkByAgencyId(Long aid);

	List<String> getLdByParkId(Long pid);

	List<HouseResourceDmenu> getRoomByLd(@Param("pid") Long pid,
	                                     @Param("bno") String bno);


	List<String> getAllResourceNameAndId();

}
