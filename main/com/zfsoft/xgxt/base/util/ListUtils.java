/**
 * @部门:学工产品事业部
 * @日期：2014-3-15 下午01:14:50 
 */
package com.zfsoft.xgxt.base.util;

import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述: List相关常用操作
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-15 下午01:14:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ListUtils {
	/**
	 * 
	 * @描述: 获取list size（过滤重复空对象list判断）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-15 下午01:16:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * Integer 返回类型 
	 */
	public static Integer getListSize(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return 0;
		}
		return list.size();
	}
}
