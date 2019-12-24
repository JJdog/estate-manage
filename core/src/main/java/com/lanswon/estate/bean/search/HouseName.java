package com.lanswon.estate.bean.search;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "house_name", type = "name",createIndex = false,useServerConfiguration = true)
public class HouseName {

	/**
	 * 主键ID
	 */
	@Id
	private Long id;

	/**
	 * 关键词
	 */
	private String name;

}