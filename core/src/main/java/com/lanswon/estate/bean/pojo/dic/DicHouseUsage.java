package com.lanswon.estate.bean.pojo.dic;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 房屋规划用途
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_house_usage")
public class DicHouseUsage extends BasePojo {

  /** 房屋规划用途 */
  @NotNull(message = "房屋规划用途不可为空")
  @NotBlank(message = "房屋规划用途不可为空")
  private String name;




}
