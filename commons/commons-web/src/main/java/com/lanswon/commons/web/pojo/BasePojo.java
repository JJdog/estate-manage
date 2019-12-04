package com.lanswon.commons.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lanswon.commons.core.serial.SnowFlake;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;


/**
 * POJO公共属性
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@ApiModel(value = "基础POJO")
public abstract class BasePojo implements Serializable {

	/** 默认用户 */
	public final static long DEFAULT_USERNAME = 0;

	/** 主键 */
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id",required = true,hidden = true)
	private long id = SnowFlake.nextId();
	/** 创建人 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long createdBy = null;
	/** 更新人 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long updatedBy = null;
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createdTime = null;
	/** 更新时间 */
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Date updatedTime = null;

}
