package com.lanswon.estate.bean.cd;

import lombok.Data;

/**
 * 房源查询条件
 *
 * @author jaswine
 */
@Data
public class HouseResourceCD extends BaseCd {

	/** 房产id */
	private long assetsId;

	/** 管理单位ID */
	private long agencyId;

	/** 楼号 */
	private String buildNo;

	/** 楼层 */
	private int buildLevel;

	/** 房间号 */
	private String buildRoom;

	/** 最小面积 */
	private double minArea;

	/** 最大面积 */
	private double maxArea;

	/** 出租 0：未 1：已*/
	private int rentStatus;

	/** 出售 0：未 1：已*/
	private int sellStatus;

}
