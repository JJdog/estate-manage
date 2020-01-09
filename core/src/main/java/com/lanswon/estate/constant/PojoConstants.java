package com.lanswon.estate.constant;

/**
 * 数据库枚举类型
 *
 * @author jaswine
 */
public class PojoConstants {


	/*==================房源====================*/

	/** 房源【闲置】*/
	public static final int RESOURCE_NO_RENT = 1;
	/** 房源【已出租】*/
	public static final int RESOURCE_HAS_RENT = 2;

	/** 房源【未出售】*/
	public static final int RESOURCE_NO_SELL = 1;
	/** 房源【出售未办证】*/
	public static final int RESOURCE_HAS_SELL_NO_PRO = 2;
	/** 房源【出售已办证】*/
	public static final int RESOURCE_HAS_SELL_HAS_PRO = 3;


	/** 房源用途【办公】*/
	public static final int RESOURCE_USAGE_BUSSINESS = 1;
	/** 房源用途【厂房】*/
	public static final int RESOURCE_USAGE_FACTORY = 2;
	/** 房源用途【商铺】*/
	public static final int RESOURCE_USAGE_SHOP = 3;

	/*==================合同====================*/

	/** 合同类型【协商】*/
	public static final int DEAL_TYPE_XS = 1;
	/** 合同类型【一事一议】*/
	public static final int DEAL_TYPE_YS = 2;
	/** 合同类型【挂靠】*/
	public static final int DEAL_TYPE_GK = 3;

	/** 合同审核【未审核】*/
	public static final int DEAL_REVIEW_NO = 1;
	/** 合同审核【通过】*/
	public static final int DEAL_REVIEW_PASS = 2;
	/** 合同审核【未通过】*/
	public static final int DEAL_REVIEW_DENIED = 3;

	/** 合同运行状态【正常】 */
	public static final int DEAL_RUN_NORMAL = 1 ;
	/** 合同运行状态【删除】 */
	public static final int DEAL_RUN_DELETE_ = 2 ;
	/** 合同运行状态【正常结束】 */
	public static final int DEAL_RUN_NORMAL_STOP = 3 ;
	/** 合同运行状态【提前结束,申请中】 */
	public static final int DEAL_RUN_ABNORMAL_STOP_APPLYING = 4 ;
	/** 合同运行状态【提前结束,已申请】 */
	public static final int DEAL_RUN_ABNORMAL_STOP_APPLIED = 5 ;

	/** 合同新签 */
	public static final int DEAL_NEW_RENT = 1;
	/** 合同续签 */
	public static final int DEAL_OLD_RENT = 2;

	/** 合同新签 */
	public static final int DEAL_YES_DICOUNT = 1;
	/** 合同续签 */
	public static final int DEAL_NO_DICOUNT = 2;


	/*==================应收款====================*/

	/** 应收款状态【未启用】*/
	public static final int MUST_NOT_AVAILABLE = 1;
	/** 应收款状态【正常】*/
	public static final int MUST_NORMAL = 2;
	/** 应收款状态【取消】*/
	public static final int MUST_CANCEL = 3;


	/*==================保证金====================*/

	/** 保证金【已启用】*/
	public static final int DEPOSIT_STATUS_ENABLED = 1;
	/** 保证金【未启用】*/
	public static final int DEPOSIT_STATUS_DISABLED = 2;


	/*==================保证金====================*/

	/** 保证金【已启用】*/
	public static final int DAMAGE_STATUS_ENABLED = 1;
	/** 保证金【未启用】*/
	public static final int DAMAGE_STATUS_DISABLED = 2;

}
