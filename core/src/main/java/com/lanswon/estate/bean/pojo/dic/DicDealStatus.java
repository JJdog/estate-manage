package com.lanswon.estate.bean.pojo.dic;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合同状态pojo
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_deal_status")
public class DicDealStatus extends BasePojo {

  @ApiModelProperty(value = "状态名称")
  private String name;

}
