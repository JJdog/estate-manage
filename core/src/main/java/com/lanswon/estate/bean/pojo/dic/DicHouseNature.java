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
 * 房屋性质
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_house_nature")
public class DicHouseNature extends BasePojo {

  /** 房屋性质名称 */
  @NotNull(message = "房屋性质名称不可为空")
  @NotBlank(message = "房屋性质名称不可为空")
  private String name;

}
