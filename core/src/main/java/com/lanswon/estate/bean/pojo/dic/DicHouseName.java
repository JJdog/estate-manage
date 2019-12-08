package com.lanswon.estate.bean.pojo.dic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dic_house_name")
@ApiModel(value = "房产名称POJO")
public class DicHouseName extends BasePojo {

  private String name;

}
