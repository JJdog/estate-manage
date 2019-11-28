package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 应收租金
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "money_rent_must")
@ApiModel(value = "应收租金POJO")
public class MoneyRentMust extends BasePojo {

	@ApiModelProperty(value = "合同id")
	private long fkDealId;
	@ApiModelProperty(value = "收租时间")
	private Date rentDate;
	@ApiModelProperty(value = "应收租金")
	private double mustCharge;
	@ApiModelProperty(value = "状态 1.未审核(未启用) 2.正常收款 3.租金取消")
	private String isEnable;

}
