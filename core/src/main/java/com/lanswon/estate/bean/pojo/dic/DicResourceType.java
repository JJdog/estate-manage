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
 * 土地用途(地类)
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_resource_type")
public class DicResourceType extends BasePojo {

  /** 土地用途(地类) */
  @NotNull(message = "房源类型不可为空")
  @NotBlank(message = "房源类型不可为空")
  private String name;

}
