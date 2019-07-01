/**
 * @部门:学工产品事业部
 * @日期：2015-6-8 下午02:22:35 
 */  
package com.zfsoft.extend.dataFormat;

/**
 * 字段格式接口
 * 
 * @author 982
 * 
 */
public interface IField {
	/**
	 * 格式检测
	 */
	public boolean check(String value);

	/**
	 * 获取错误信息
	 *
	 */
	public String getErrorMessage(String value);

}
