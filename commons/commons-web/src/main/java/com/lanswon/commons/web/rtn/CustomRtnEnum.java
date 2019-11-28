package com.lanswon.commons.web.rtn;


import lombok.Getter;

/**
 * 一般返回枚举类
 *
 * @author jaswine
 */
public enum CustomRtnEnum {

	/** 成功 */
	SUCCESS(200,"成功"),
	/** 异常 */
	ERROR(400,"异常"),
	/** SQL错误 */
	ERROR_BAD_SQL(10000,"SQL错误"),
	/** 查询为空 */
	ERROR_EMPTY_RESULT(10001,"查询为空"),
	/** 资源不存在 */
	RESOURCE_NON_EXIST(10002,"资源不存在");


	@Getter
	private int status;
	@Getter
	private String msg;



	CustomRtnEnum(int status, String msg){
		this.status = status;
		this.msg = msg;
	}

}
