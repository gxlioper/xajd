/**
 * @部门:学工产品事业部
 * @日期：2015-4-17 下午04:08:23 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2015-4-17 下午04:08:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TyxsZzjgService extends SuperServiceImpl<TyxsZzjg, TyxsZzjgDao> {
	/**
	 * 
	 * @描述:按学号、学年查询记录总数
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-23 下午05:14:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzjg t) {

		return dao.getCountByXhAndXn(t);
	}

	/**
	 * 
	 * @描述:根据ID查询结果信息
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-23 下午05:15:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZzjgById(String id) {
		return dao.getZzjgById(id);
	}
	
	public HashMap<String, String> getDjbById(String id) {
		return dao.getDjbById(id);
	}

}
