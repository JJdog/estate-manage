package com.lanswon.estate.bean.pojo.backup;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合同和房源关联关系
 *
 * @author jaswine
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@TableName(value = "backup_mid_deal_house")
public class BackupMidDealHouse {

	  /** 合同id */
	  private Long fkDealId;
	  /** 房源名称 */
	  private String resourceName;
	  /** 房源id */
	  private Long fkResourceId;
	  /** 房源面积 */
	  private Double resourceArea;
	  /** 实际价格 */
	  private Double realMoney;
	  /** 用途 */
	  private Long resourceUsage;


}
