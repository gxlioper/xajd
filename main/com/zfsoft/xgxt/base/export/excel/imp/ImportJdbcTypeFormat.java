package com.zfsoft.xgxt.base.export.excel.imp;

import java.util.Date;

import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;

/**
 * 导入数据库类型格式化   只支持Mybatis类型对应
 * @author Administrator
 *
 */
public class ImportJdbcTypeFormat {
	
	/**
	 * 导入参数格式化
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object parameterFormat(String value,String type) throws Exception{
		if (StringUtils.isNull(value) || StringUtils.isNull(type)){
			return value;
		} 
		
		if("VARCHAR2".equals(type) || "VARCHAR".equals(type)){
			return value;
		}else if("NUMBER".equals(type)){
			return formatNumber(value);
		}else if("DATE".equals(type)){
			return formatDate(value);
		}else{
			return value;
		}
			
	}
	
	/**
	 * 格式化数据类型Number
	 * @param value
	 * @return
	 */
	private static Object formatNumber(String value) throws Exception{
		return Integer.valueOf(value);
	}
	
	/**
	 * 格式化数据类型Date
	 * @param value
	 * @return
	 */
	private static Object formatDate(String value) throws Exception{
		Date date = DateUtils.parse(value);
		return date;
	}
	
}
