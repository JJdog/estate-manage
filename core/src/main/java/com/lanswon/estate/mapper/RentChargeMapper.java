package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.pojo.RentCharge;
import com.lanswon.estate.bean.vo.MonthRentChargeVO;
import com.lanswon.estate.bean.vo.SimpleWarnRentVO;
import com.lanswon.estate.bean.vo.report.RentChargeHasDateAndLastNoDateVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 租金Mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface RentChargeMapper extends BaseMapper<RentCharge> {

	/**
	 * 每个交租点信息
	 * @param id 合同id
	 * @return 交租店租金信息
	 */
	List<MonthRentChargeVO> getRentChargeInfoByDealId(@Param(value = "id") long id);

	/**
	 * 冻结租金
	 * @param id 合同id
	 * @param date 冻结开始时间
	 * @return boolean
	 */

	boolean freezeRentCharge(@Param("id") long id,
	                         @Param("date") Date date);

	/**
	 * 尾款租金
	 * @param id 合同id
	 * @param date 结束时间
	 * @return 尾款
	 */
	double generateTail(@Param("id") long id,
	                    @Param("date") Date date);

	@Delete("DELETE FROM money_rent_must WHERE fk_deal_id = #{id} ")
	boolean deleteByDealId(@Param("id") long id);

	@Update("UPDATE money_rent_must t SET t.is_enable = 1 WHERE t.fk_deal_id = #{id} ")
	boolean enableRentCharge(long id);

	@Update("UPDATE money_rent_must t SET t.is_enable = 3 WHERE t.fk_deal_id = #{id} ")
	boolean recallRentChargeByDealId(long id);

	IPage<SimpleWarnRentVO> getRentWarnPage(@Param("page") Page<Object> objectPage,
	                                        @Param("asc") int asc ,
	                                        @Param("day") int day);

	@Update("UPDATE money_rent_must t SET t.actual_charge = #{money} WHERE t.id = #{id} ")
	boolean pay4Rent(@Param("id") long id,
	                 @Param("money") double money);

	@Select("SELECT t.rent_date  FROM money_rent_must t WHERE t.is_enable = 1 ORDER BY t.rent_date DESC LIMIT 1")
	Date getLastHasRentedDateByDealId(long id);

	@Select("SELECT t.rent_date FROM money_rent_must t WHERE t.is_enable = 3 ORDER BY t.rent_date ASC ")
	Date getNextRentDate(long id);
}
