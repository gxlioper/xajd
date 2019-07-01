/**
 * @部门:学工产品事业部
 * @日期：2014-3-6 上午10:00:52 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-6 上午10:00:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IDelete {
	/**
	 * 成功删除条数key
	 */
	String _CGTS = "successDel";
	/**
	 * 错误消息集合key
	 */
	String _ERROE_OBJ = "errorObject";
	/**
	 * 不存在错误消息
	 */
	String _UNHAVE_ERROE = "-1";

	/**
	 * 
	 * @描述: 是否可删除
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 上午10:31:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 *            主键
	 * @return boolean 返回类型
	 */
	boolean isCanDelete(String pk) throws Exception;

	/**
	 * @描述: 不能删除提示所需参数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 上午10:31:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return Map<String,String> 返回类型
	 */
	Map<String, String> showMessage(String pk)throws Exception;
	/**
	 * 
	 * @描述: 扩展可用于修改等操作
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午05:34:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 */
	int execute(String[] ids) throws Exception;
}
