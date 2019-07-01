package com.zfsoft.xgxt.base.export.excel.imp;

import java.util.Date;

import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;

/**
 * �������ݿ����͸�ʽ��   ֻ֧��Mybatis���Ͷ�Ӧ
 * @author Administrator
 *
 */
public class ImportJdbcTypeFormat {
	
	/**
	 * ���������ʽ��
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
	 * ��ʽ����������Number
	 * @param value
	 * @return
	 */
	private static Object formatNumber(String value) throws Exception{
		return Integer.valueOf(value);
	}
	
	/**
	 * ��ʽ����������Date
	 * @param value
	 * @return
	 */
	private static Object formatDate(String value) throws Exception{
		Date date = DateUtils.parse(value);
		return date;
	}
	
}
