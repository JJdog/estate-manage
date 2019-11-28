package com.lanswon.commons.web.rtn;

import com.lanswon.commons.web.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;


/**
 * 带实体消息的DTO
 *
 * @param <T> 实体消息
 *
 * @author jaswine
 */
public class DataRtnDTO<T> extends AbstractDTO {


	public DataRtnDTO(){}

	public DataRtnDTO(int status, String msg, T data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	@Getter
	@Setter
	private T data;


}
