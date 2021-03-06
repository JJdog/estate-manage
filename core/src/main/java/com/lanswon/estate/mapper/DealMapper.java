package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.estate.bean.cd.DealCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.pojo.backup.BackupDeal;
import com.lanswon.estate.bean.vo.*;
import com.lanswon.estate.bean.vo.doc.DealTemp;
import com.lanswon.estate.bean.vo.page.DealPage;
import com.lanswon.estate.bean.vo.page.NoRentDealPage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface DealMapper extends BaseMapper<Deal> {

	IPage<DealPage> getDealInfoPage(@Param(value = "page") Page<Object> page,
	                                @Param(value = "cd") DealCD cd);

	DealTemp getDealWordInfo(long id);

	@Update("UPDATE deal t SET t.deal_exist_status = #{status} WHERE t.id = #{did} ")
	boolean updateDealRunStatus(@Param("did") Long did,
	                            @Param("status") Integer status);

	/**
	 * 审核合同
	 * @param id 合同id
	 * @param status 审核状态
	 * @return 审核结果
	 */
	@Update("UPDATE deal t SET t.deal_review_status = #{status} WHERE t.id = #{id}")
	boolean updateDealReviewStatus(@Param("id") Long id,
	                               @Param("status") Integer status);

	/**
	 * 终止合同(提前)
	 * @param id 合同id
	 * @return 合同
	 */
	@Update("UPDATE deal t SET  deal_exist_status = #{status} WHERE t.id = #{id} ")
	boolean stopDeal(@Param("id") long id,
	                 @Param("status") int status);

	/**
	 * 依据合同id获得房源id
	 * @param id 合同id
	 * @return 房源id
	 */
	List<Long> getHouseResourceIdByDealId(long id);

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
	IPage<DealPage> getNoReviewDealInfoPage(Page<Object> objectPage,
	                                              @Param("cd") DealCD cd);
	IPage<DealPage> getStopApply(Page<Object> objectPage,
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

	@Select("SELECT t.pay_type FROM deal t WHERE t.id = #{id} ")
	int getPermonthByDealId(long id);


	double getMonthRentByDealId(long id);

	Date getDealEndtimeByHouseId(Long hid);


	BackupDeal getDealInfo2Backup(long id);
}
