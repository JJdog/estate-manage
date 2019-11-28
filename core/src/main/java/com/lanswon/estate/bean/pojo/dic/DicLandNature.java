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
 * 土地使用权类型
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_land_nature")
public class DicLandNature extends BasePojo {

  /** 土地使用权类型 */
  @NotNull(message = "土地使用权类型不可为空")
  @NotBlank(message = "土地使用权类型不可为空")
  private String name;



}
