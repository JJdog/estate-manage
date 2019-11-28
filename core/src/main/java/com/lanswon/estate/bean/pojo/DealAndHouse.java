package com.lanswon.estate.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合同和房源
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "mid_deal_house")
public class DealAndHouse extends BasePojo {

	private long fkDealId;

	private long fkHouseResourceId;
}
