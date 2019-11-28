package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 土地资产VO
 *
 * @author jaswine
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandAssetsVO {

		@ApiModelProperty(value = "主键id")
		private String id;
		/** 产权名称 */
		private String assetsName;
		@ApiModelProperty(value = "产权拥有人id")
		private String fkOwnId;
		/** 产权拥有人 */
		private String owner;
		/** 坐落 */
		private String assetsLocation;
		/** 地号 */
		private String landNum;
		/** 图号 */
		private String picNum;
		// todo
		/** 地类(用途) */
		private long useTypeCode;

		private String useType;

		/** 取得价格 */
		private double money;

		/** 使用权类型 */
		private long useRightCode;

		private String useRight;
		/** 终止时间 */
		@JsonFormat(pattern = "yyyy年MM月dd日")
		private Date endTime;
		/** 使用权面积 */
		private double assetsQueue;
		/** 独有面积 */
		private double selfQueue;
		/** 公摊面积 */
		private double shareQueue;
		/** 备注 */
		private String remark;



}
