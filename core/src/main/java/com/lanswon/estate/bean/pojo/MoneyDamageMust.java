package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 违约金pojo
 *
 * @author jaswine
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("money_damage_must")
public class MoneyDamageMust extends BasePojo {

	/** 合同id */
    private Long fkDealId;
    /** 违约金 */
    private Double money;
    /** 应收日期 */
    private Date mustDate;
    /** 启用状态 */
    private Integer isEnable;


}
