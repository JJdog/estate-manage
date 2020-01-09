package com.lanswon.estate.constant;


/**
 * 数据库字段表
 *
 * @author jaswine
 */
public class DatabaseConstants {

	/** ID */
	public static final String BASE_ID = "id";
	/** 创建时间 */
	public static final String BASE_CT = "created_time";
	/** 更新时间 */
	public static final String BASE_UT = "updated_time";
	/** 创建人 */
	public static final String BASE_CU = "created_by";
	/** 更新人 */
	public static final String BASE_UU = "updated_by";

	//////LandAssets//////



	//////sharetype//////

	public static final String SHARE_NAME = "name";


	/*=========合同房源表============*/

	/** 合同ID */
	public static final String MID_DEAL_HOUSE_FK_DEAL_ID = "fk_deal_id";
	/** 房源ID */
	public static final String MID_DEAL_HOUSE_FK_HOUSE_ID = "fk_house_resource_id";

	/*=========合同房源表============*/

	/** 合同ID */
	public static final String MONEY_DEPOSIT_MUST_FK_DEAL_ID = "fk_deal_id";



}
