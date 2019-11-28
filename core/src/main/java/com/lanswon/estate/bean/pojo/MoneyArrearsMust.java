package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应收往年欠款
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "money_arrears_must")
@ApiModel(value = "往年欠款POJO")
public class MoneyArrearsMust extends BasePojo {

	@ApiModelProperty(value = "合同id")
    private long fkDealId;

	@ApiModelProperty(value = "欠款")
    private double arrears;

}
