/**
 * @部门:学工产品事业部
 * @日期：2013-12-2 下午04:37:53 
 */
package com.zfsoft.xgxt.base.export.config;

import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 配置导入生成规则
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-2 下午04:37:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface ConfigRule {
	/**
	 * 
	 * @描述:获取列名对应的验证规则列表 
	 * <br>Map<列名称,验证规则id>
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-2 下午04:41:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return Map<String,String> 返回类型
	 */
	public Map<String, String> getMyRrule();
}
