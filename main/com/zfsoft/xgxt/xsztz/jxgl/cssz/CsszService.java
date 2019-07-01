/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:29:30 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-1-27 上午10:13:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	
	public CsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}

}
