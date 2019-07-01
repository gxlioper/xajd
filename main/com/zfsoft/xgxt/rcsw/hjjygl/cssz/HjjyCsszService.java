/**
 * @部门:学工产品事业部
 * @日期：2016-05-07下午04:29:30 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.cssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2016-05-07下午04:29:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjyCsszService extends SuperServiceImpl<HjjyCsszForm, HjjyCsszDao> {
	
	public HjjyCsszForm getModel() throws Exception{
		return dao.getModel();
	}

	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
	public List<HashMap<String, String>> getJyyyList() throws Exception{
		return dao.getJyyyList();
	}


}
