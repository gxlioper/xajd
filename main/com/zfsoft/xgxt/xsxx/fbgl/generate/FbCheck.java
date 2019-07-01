/**
 * @部门:学工产品事业部
 * @日期：2014-4-11 下午03:43:15 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 检查数据是否合法
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-11 下午03:43:15
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbCheck {
	/**
	 * 
	 * @描述: 检测字符串长度
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午03:45:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @param xzcd
	 * @return boolean 返回类型
	 */
	public boolean checkLength(String value, int xzcd) {
		if (value.length() <= xzcd) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @描述: 检测字符串长度
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午03:45:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @param xzcd
	 * @return boolean 返回类型
	 */
	public boolean checkLength(Integer value, int xzcd) {
		if (value.toString().length() <= xzcd) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @描述: 检测内容是否超过数据库列长度
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午04:23:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tableName
	 * @param columnName
	 * @param nowValue
	 * @return boolean 返回类型
	 */
	public boolean checkTableColumLength(String tableName, String columnName,
			String nowValue) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from all_TAB_COLUMNS ");
		sb.append(" where table_name=? and column_name=?");
		HashMap<String, String> hm = DAO.getInstance().getMapNotOut(
				sb.toString(), new String[] { tableName.toUpperCase(), columnName.toUpperCase() });
		String column_length = hm.get("data_length");
		// 长度小于限制长度
		if (nowValue.length() <= Integer.parseInt(column_length)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @描述: 检测值的唯一性
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午04:28:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tableName
	 * @param columnName
	 * @param nowValue
	 * @return boolean 返回类型
	 */
	public boolean checkUniqeKey(String tableName, String columnName,
			String nowValue) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(tableName);
		sb.append(" where ");
		sb.append(columnName);
		sb.append("=?");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { nowValue });
		if (null == list || list.size() <= 0) {
			return true;
		}
		return false;
	}
}
