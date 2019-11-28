package com.lanswon.commons.web.rtn;

import com.lanswon.commons.web.dto.AbstractDTO;
import lombok.ToString;


/**
 * 返回结果DTO对象
 * @author jaswine
 */
@ToString
public class SimpleRtnDTO extends AbstractDTO {

	public SimpleRtnDTO(){}

	public SimpleRtnDTO(int code, String msg){
		super(code,msg);
	}


}
