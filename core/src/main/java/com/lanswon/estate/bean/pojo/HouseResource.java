package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 房源POJO
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "house_resource")
@ApiModel(value = "房源POJO")
public class HouseResource extends BasePojo {


  @NotNull(message = "房源必须绑定房产信息")
  @ApiModelProperty(value = "房产id")
  private long fkHouseAssetsId;

  @NotNull(message = "管理单位不可为空")
  private long fkAgencyId;

  @NotBlank(message = "园区信息不可为空")
  @ApiModelProperty(value = "园区")
  private String park;

  @ApiModelProperty(value = "楼栋号")
  private String buildNo;

  @ApiModelProperty(value = "楼层")
  private long buildLevel;

  @ApiModelProperty(value = "房间号")
  private String buildRoom;

  @NotNull(message = "房源面积不可为空")
  @ApiModelProperty(value = "房源面积")
  private double resourceArea;

  @ApiModelProperty(value = "原来租金")
  private double originRentCharge;

  @NotNull(message = "房源指导价不可为空")
  @ApiModelProperty(value = "指导价")
  private double guideRentCharge;

  @NotNull(message = "实际租金不可为空")
  @ApiModelProperty(value = "实际租金")
  private double realRentCharge;

  @ApiModelProperty(value = "每平方米月租金",hidden = true)
  private double rentMoneyPerArea;

  @ApiModelProperty(value = "出租状态")
  private int rentStatus;

  @ApiModelProperty(value = "出售状态")
  private int sellStatus;

  @ApiModelProperty(value = "备注")
  private String remark;


}
