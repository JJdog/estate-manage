package com.lanswon.estate.bean.pojo.backup;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合同备份内容
 *
 * @author jaswine
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@TableName(value = "backup_deal")
public class BackupDeal extends BasePojo {

	/** 合同id */
	private Long fkDealId;
	/** 甲方名称 */
	private String jfName;
	/** 乙方名称 */
	private String yfName;

}
