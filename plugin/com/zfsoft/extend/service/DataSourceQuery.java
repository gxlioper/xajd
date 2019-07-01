/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午05:25:54 
 */  
package com.zfsoft.extend.service;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午05:25:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public abstract class DataSourceQuery {

	/**
	 * 
	 * @描述:获取数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-8 上午09:39:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public abstract List<HashMap<String,String>> getData();
}
