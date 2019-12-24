package com.lanswon.estate.bean.vo.report.comp;

import lombok.Data;

@Data
public class CompResourceArea {

	/** 正常出租 */
	private Double normalRentArea;
	/** 长期无收益 */
	private Double longRentArea;
	/** 公共设施 */
	private Double publicRentArea;
	/** 车库 */
	private Double garageRentArea;
	/** 完全自用 */
	private Double selfRentArea;
	/** 小计 */
	private Double totalRentArea;


	/** 闲置 */
	private Double noRentArea;
	/** 出售未过户 */
	private Double selledNoProArea;
}
