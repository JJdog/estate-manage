package com.lanswon.estate.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 合同和房源
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "mid_deal_house")
@ApiModel(value = "合同房源对应关系")
public class DealAndHouse extends BasePojo {

	@ApiModelProperty(value = "合同id",hidden = true)
	private Long fkDealId;

	@NotNull(message = "房源不可为空")
	@ApiModelProperty(value = "房源id",required = true)
	private Long fkHouseResourceId;

	@NotNull(message = "实际租金不可为空")
	@ApiModelProperty(value = "实际租金(元/月/平方米)",required = true)
	private Double realMoney;
}
