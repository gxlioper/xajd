/**
 * @部门:学工产品事业部
 * @日期：2014-3-17 上午09:38:54 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-17 上午09:38:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IOrderBy {
	/**
	 * 
	 * @描述: 获取基础sql语句
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-17 下午03:12:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBaseSql();
	/**
	 * 
	 * @描述: 获取sql对应参数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-17 下午03:12:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getParam();
	/**
	 * 
	 * @描述: 获取排序后的数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-17 下午03:13:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjPzxx 条件规则信息
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGroupByData(
			List<HashMap<String, String>> tjPzxx);
}
