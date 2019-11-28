package com.lanswon.estate.bean.cd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 土地产证queryCondition
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandAssetsCD extends BaseCd {

	@ApiModelProperty(value = "使用权人")
	private long ownId;
	@ApiModelProperty(value = "地号")
	private long landNum;
	@ApiModelProperty(value = "坐落")
	private String location;
}
