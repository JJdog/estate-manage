package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 应收保证金
 *
 * @author jaswine
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "money_deposit_must")
@ApiModel(value = "应收保证金POJO")
public class MoneyDepositMust extends BasePojo {

	@ApiModelProperty(value = "合同id")
    private long fkDealId;

	@ApiModelProperty(value = "保证金")
    private double deposit;



}
