package com.lanswon.estate.bean.cd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 确认退租cd
 *
 * @author jaswine
 */
@Data
@ApiModel(value = "确认退租的载体")
public class DealStopOkCD extends BaseCd{

	@ApiModelProperty(value = "合同id")
	private Long id;

	@ApiModelProperty(value = "违约金")
	private Double money;

	@ApiModelProperty(value = "应收时间")
	private Date mustDate;

}
