/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:40:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyJcszService extends SuperServiceImpl<ZzdyJcszForm, ZzdyJcszDao> {
	private ZzdyJcszDao dao = new ZzdyJcszDao();
	
	public boolean isHave(ZzdyJcszForm model) {
		return dao.isHave(model);
	}
	
	public boolean isUsed(String[] values) {
		return dao.isUsed(values);
	}
	
	public boolean editSzxm(ZzdyJcszForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public List<HashMap<String,String>> getXmList(String xn,String xq) throws Exception {
		return dao.getXmList(xn,xq);
	}

}
