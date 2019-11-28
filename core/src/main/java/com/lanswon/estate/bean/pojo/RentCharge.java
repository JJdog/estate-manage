package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 租金信息表
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "must_rent")
public class RentCharge extends BasePojo {


  @ApiModelProperty(value = "合同id")
  private long fkDealId;

  @ApiModelProperty(value = "交租时间")
  private Date rentDate;

  @ApiModelProperty(value = "年份")
  private int rentYear;

  @ApiModelProperty(value = "月份")
  private long rentMonth;

  @ApiModelProperty(value = "应付款")
  private double mustCharge;

  @ApiModelProperty(value = "实收款")
  private double actualCharge;

  @ApiModelProperty(value = "欠款")
  private double arrears;

  @ApiModelProperty(value = "租金状态")
  private int rentStatus;


}
