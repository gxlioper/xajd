/**
 * @部门:学工产品事业部
 * @日期：2015-8-7 上午10:41:06 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtjg;

import java.util.HashMap;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-8-7 上午10:41:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstRtjgService extends SuperServiceImpl<YstRtjgForm, YstRtjgDao> {
	
	//获取社团信息
	public HashMap<String, String> getYstxxMap(YstRtjgForm t) throws Exception{
		return dao.getYstxxMap(t);
	}
	public boolean editYstRtjg(YstRtjgForm model) throws Exception {
		model.setRtxn(Base.currXn);
		model.setRtxq(Base.currXq);
		boolean result = true;
		if ("save".equals(model.getType())) {
			String rtid = UniqID.getInstance().getUniqIDHash();
			model.setRtid(rtid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			
		}
		return result;
	}
	
	
}
