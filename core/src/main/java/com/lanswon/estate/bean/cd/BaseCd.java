package com.lanswon.estate.bean.cd;


import lombok.Data;

/**
 * 基础条件
 *
 * @author jaswine
 */
@Data
public abstract class BaseCd {

	public int page;
	public int limit;
	public int asc;
	public long aid;
}
