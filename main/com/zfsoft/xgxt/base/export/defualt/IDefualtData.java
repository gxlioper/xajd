/**
 * @部门:学工产品事业部
 * @日期：2014-1-16 上午10:36:21 
 */
package com.zfsoft.xgxt.base.export.defualt;

import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 下载模板
 * @类功能描述: 默认数据接口
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-16 上午10:36:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IDefualtData {
	/**
	 * @描述: 获取模板默认数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-16 上午10:36:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<String[]> String数组为行数据，
	 * @throws
	 */
	List<String[]> getDefualtData(String drmkdm);
}
