/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 上午10:48:18 
 */
package com.zfsoft.xgxt.base.extend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 批量接口
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-19 上午10:48:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IExecute {
	/**
	 * 
	 * @描述:业务方法执行(逐条验证)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 上午10:49:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return 是否可以执行 boolean 返回类型
	 */
	public String execute(Object data);

	/**
	 * 
	 * @描述:执行后提示信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 上午10:50:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	public String message(Object data, String ywMessage);

	/**
	 * 
	 * @描述: 验证所有(扩展验证，返回true才执行execute业务验证)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 下午04:04:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 *            验证数据（一般为list）
	 * @return String 返回类型 true|提示信息
	 */
	public String allCheck(Object data);
}
