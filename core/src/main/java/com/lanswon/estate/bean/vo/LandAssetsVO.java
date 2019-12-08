package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
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

	private String id;
	/** 产权名称 */
	private String assetsName;
	/** 产权人id */
	private String fkOwnId;

	/** 产权拥有人 */
	private String owner;

	private String agencyName;
	private Long fkAgencyId;

	/** 土地证号 */
	private String landNo;

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
	private double assetsArea;
	/** 独有面积 */
	private double selfArea;
	/** 公摊面积 */
	private double shareArea;

	private double yzArea;

	private double wzArea;

	/** 备注 */
	private String remark;



}
