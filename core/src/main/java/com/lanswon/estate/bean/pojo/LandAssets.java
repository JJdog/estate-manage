package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 资产信息
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "land_assets")
@ApiModel(value = "土地资产POJO")
public class LandAssets extends BasePojo {

	/**
	 * 产权拥有人
	 */
	private long fkOwnId;
	/**
	 * 坐落
	 */
	private String assetsLocation;

	private String landNo;

	private Long fkAgencyId;

	/**
	 * 地号
	 */
	private String landNum;
	/**
	 * 图号
	 */
	private String picNum;
	/**
	 * 地类(用途)
	 */
	private Long fkLandUsageId;
	/**
	 * 使用权类型
	 */
	private long fkLandNatureId;
	/**
	 * 取得价格
	 */
	private double money;
	/**
	 * 终止时间
	 */
	@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss:ms")
	private Date endTime;
	/**
	 * 使用权面积
	 */
	private double assetsArea;
	/**
	 * 独有面积
	 */
	private double selfArea;
	/**
	 * 公摊面积
	 */
	private double shareArea;

	private double yzArea;

	private double wzArea;
	/**
	 * 备注
	 */
	private String remark;

}
