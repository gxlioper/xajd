/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 下午06:00:43 
 */
package com.zfsoft.xgxt.ystgl.stzhwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-14 下午06:00:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StzhwhService extends SuperServiceImpl<StzhwhForm, StzhwhDao> {
	// 暂定当前学年学期的维护数据
	public List<HashMap<String, String>> getzhwhList(String xn, String stid,
			String tnzt) {
		return dao.getzhwhList(xn, stid, tnzt);
	}

	// 成员状态维护list
	public List<HashMap<String, String>> getStCyZtWhList(String stid) {
		return dao.getStCyZtWhList(stid);
	}

	public List<HashMap<String, String>> getStzhwhList(StzhwhForm t, User user)
			throws Exception {
		return dao.getStzhwhList(t, user);
	}
	
	public List<HashMap<String, String>> getCycjlist(String stid,String xh){
		return dao.getCycjlist(stid,xh);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除成绩
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-11 下午07:02:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param stid
	 * @param xh
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int delCycj(String[] values) throws Exception{
		return dao.delCycj(values);
	}
}
