package com.lanswon.commons.core.poi;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;


/**
 * 文件I/O操作
 *
 * @author jaswine
 */
@Slf4j
public class FileIOUtil {


	public static <T> List<T> importFile(File file,int titleRows,int headerRows,Class<T> pojoClass){

		if (!file.exists()){
			log.error("文件不存在！！！");
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);

		List<T> pojoList = ExcelImportUtil.importExcel(file, pojoClass, params);

		return pojoList;

	}

	public static <T> List<T> importFile(InputStream inputStream, int titleRows, int headerRows, Class<T> pojoClass) throws Exception {


		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);

		List<T> pojoList = ExcelImportUtil.importExcel(inputStream, pojoClass, params);

		return pojoList;

	}


	public static <T> Workbook exportFile(String name,Class<T> clazz, Collection<T> data){

		ExportParams params = new ExportParams();
		params.setTitle(name);
		Workbook excel = ExcelExportUtil.exportBigExcel(params, clazz, data);
		return excel;
	}

}
