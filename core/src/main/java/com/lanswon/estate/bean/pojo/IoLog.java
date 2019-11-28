package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导入日志对象
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "io_log")
@ApiModel(value = "日志导入对象")
public class IoLog extends BasePojo {


  @ApiModelProperty(value = "日志名称")
  private String logName;

  @ApiModelProperty(value = "日志类型")
  private long type;

  @ApiModelProperty(value = "日志信息")
  private String msg;

  @ApiModelProperty(value = "总数")
  private int totalNum;

  @ApiModelProperty(value = "成功数")
  private long successNum;

  @ApiModelProperty(value = "失败数")
  private long errorNum;


}
