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
 * 房屋共有情况
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_house_share")
public class DicHouseShare extends BasePojo {


  /** 房屋共有情况 */
  @NotNull(message = "房屋共有情况不可为空")
  @NotBlank(message = "房屋共有情况不可为空")
  private String name;


}
