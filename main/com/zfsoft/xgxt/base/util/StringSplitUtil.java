/**
 * @部门:学工产品事业部
 * @日期：2013-11-22 上午11:42:01 
 */
package com.zfsoft.xgxt.base.util;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: String类型分隔
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-22 上午11:42:01
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StringSplitUtil {
	/**
	 * 
	 * @描述:数组转换成字符串，以splitFlag分隔
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-22 上午11:45:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param input
	 * @param splitFlag 分隔符
	 * @return String 返回类型
	 */
	public static String arrayToStr(String input[], String splitFlag) {
		if (null == input || input.length <= 0) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			str.append(input[i]);
			if (i + 1 != input.length) {
				str.append(splitFlag);
			}
		}
		return str.toString();
	}

	/**
	 * 
	 * @描述:数组转换成字符串,以逗号分隔
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-22 上午11:47:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param input
	 * @return String 返回类型
	 */
	public static String arrayToStr(String input[]) {
		return arrayToStr(input, ",");
	}

	/**
	 * 
	 * @描述:字符串转换成数组
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-22 上午11:45:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @param splitFlag 分隔符
	 * @return String[] 返回类型
	 */
	public static String[] strToArray(String str, String splitFlag) {
		if (null == str) {
			return null;
		}
		String[] inputs = str.split(splitFlag);
		return inputs;
	}

	/**
	 * 
	 * @描述:字符串转换成数组
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-22 上午11:47:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return String[] 返回类型
	 */
	public static String[] strToArray(String str) {
		return strToArray(str, ",");
	}
}
