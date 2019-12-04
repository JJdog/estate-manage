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
 * 实收流水
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "money_real_flow")
@ApiModel(value = "实收流水POJO")
public class MoneyRealFlow extends BasePojo {

	@ApiModelProperty(value = "凭证号")
    private String certNo;

	@ApiModelProperty(value = "对应应收id")
    private long fkMustMoneyId;

	@ApiModelProperty(value = "实收日期")
    private Date moneyDate;

	@ApiModelProperty(value = "实收金额")
    private double money;

	@ApiModelProperty(value = "备注")
	private String remark;

}
