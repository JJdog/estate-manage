package com.lanswon.commons.core.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Json工具类
 * <p>
 *     Json工具类:使用Jackson对Json对象的操作进行了简单的封装
 * </p>
 *
 * @author Jaswine
 **/
public class JsonUtil {

	/** 转换对象 */
	private static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtil() {}

	/**
	 * Object转json字符串
	 *
	 * @param obj 对象
	 * @param <T>
	 * @return json字符串
	 */
	public static <T> String obj2String(T obj) {
		String s = null;
		if (obj == null) {
			return s;
		}
		try {
			s = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * Object转json字符串并格式化美化
	 *
	 * @param obj 对象
	 * @param <T>
	 * @return json字符串
	 */
	public static <T> String obj2StringPretty(T obj) throws Exception {
		if (obj == null) {
			return null;
		}
		return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

}
