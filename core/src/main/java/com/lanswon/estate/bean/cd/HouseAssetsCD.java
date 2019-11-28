package com.lanswon.estate.bean.cd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房屋产证查询条件
 *
 * @author jaswine
 */
@Data
public class HouseAssetsCD  extends BaseCd {

	@ApiModelProperty(value = "所有权人id")
	private long ownerId;

	@ApiModelProperty(value = "管理单位id")
	private long agencyId;

	@ApiModelProperty(value = "地址")
	private String location;

}
