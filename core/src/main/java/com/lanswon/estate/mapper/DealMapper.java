package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.DealCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.vo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DealMapper extends BaseMapper<Deal> {

	IPage<DealPage> getDealInfoPage(@Param(value = "page") Page<Object> page,
	                                @Param(value = "cd") DealCD cd);

	DetailDealVO getDetailDealInfo(long id);

	/**
	 * 审核合同
	 * @param id 合同id
	 * @param status 审核状态
	 * @return 审核结果
	 */
	@Update("UPDATE deal t SET t.deal_review_status = #{status} WHERE t.id = #{id}")
	boolean reviewDeal(@Param("id") long id,
	                   @Param("status") int status);

	/**
	 * 终止合同(提前)
	 * @param id 合同id
	 * @return 合同
	 */
	@Update("UPDATE deal t SET  deal_exist_status = 4 WHERE t.id = #{id} ")
	boolean stopDeal(long id);

	/**
	 * 依据合同id获得房源id
	 * @param id 合同id
	 * @return 房源id
	 */
	@Select("SELECT t.fk_house_resource_id FROM deal t WHERE t.id = #{id} ")
	long getHouseResourceIdByDealId(long id);

	/**
	 * 获得合同的状态(租\售 状态)
	 * @param id 合同id
	 * @return 状态
	 */
	@Select("SELECT t.deal_exist_status ,t.deal_review_status FROM deal t WHERE t.id = #{id} ")
	DealStatusVO getDealStatusById(long id);

	/**
	 * 删除合同
	 * @param id 合同id
	 * @return 删除
	 */
	@Update("UPDATE deal t SET  t.deal_exist_status = 2 WHERE t.id = #{id} ")
	boolean changeExistStatus2Deleted(long id);

	/**
	 * 获得未审核的合同
	 * @param objectPage 分页
	 * @param cd 查询条件
	 * @return 合同s
	 */
	IPage<NoRentDealPage> getNoReviewDealInfoPage(Page<Object> objectPage,
	                                        @Param("cd") DealCD cd);

	/**
	 * 获得预警合同信息
	 * @param objectPage 分页
	 * @param asc 排序
	 * @param day 预警天数
	 * @return 预警合同
	 */
	IPage<SimpleWarnDealVO> getSimpleWarnDeal(Page<Object> objectPage,
	                                          @Param("asc") int asc,
	                                          @Param("day") int day);


	List<MustMoneyVO> getMustMoneyByDealId(long id);

	List<TransFlowVO> getTransFlowByDealId(long id);

	@Select("SELECT t.deal_serial FROM deal t ORDER BY created_time DESC LIMIT 1")
	String getLatestSeqByType();

}
