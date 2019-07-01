/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午03:25:45 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-28 下午03:25:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzCsszService extends SuperServiceImpl<JcftzCsszForm, JcftzCsszDao>{
	public JcftzCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
}
