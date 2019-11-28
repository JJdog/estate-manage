package com.lanswon.estate.bean.vo;


import lombok.Data;

/**
 * 合同状态Vo
 *
 * @author jaswine
 */
@Data
public class DealStatusVO {

	/** 审核状态 */
	private int dealReviewStatus;

	/** 运行状态 */
	private int dealExistStatus;

}
