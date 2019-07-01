/**
 * @部门:学工产品事业部
 * @日期：2017年3月27日 下午1:50:25 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年3月27日 下午1:50:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhjlService extends SuperServiceImpl<LxmdwhjlForm,LxmdwhjlDao>{

	/** 
	 * @描述:根据id查询留校名单维护记录信息（关联留校名单信息）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午5:11:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jlid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getLxmdwhjlById(String jlid) {
		return dao.getLxmdwhjlById(jlid);
	}

}
