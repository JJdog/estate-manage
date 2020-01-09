package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 保证金退还记录POJO
 *
 * @author jaswine
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "money_deposit_return")
@ApiModel(value = "保证退还记录")
public class MoneyDepositReturn extends BasePojo {

	/** 合同id */
	@ApiModelProperty(value = "合同id")
    private Long fkDealId;

	/** 退还的钱 */
	@ApiModelProperty(value = "退还的钱")
    private Double money;

	/** 退还时间 */
	@ApiModelProperty(value = "退还时间")
    private Date returnDate;


}
