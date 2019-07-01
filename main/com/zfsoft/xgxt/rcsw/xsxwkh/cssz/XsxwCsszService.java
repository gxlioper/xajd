/**
 * @部门:学工产品事业部
 * @日期：2016-05-07下午04:29:30 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生行为考核参数设置
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午02:38:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XsxwCsszService extends SuperServiceImpl<XsxwCsszForm, XsxwCsszDao> {
	
	public XsxwCsszForm getModel() throws Exception{
		return dao.getModel();
	}

	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	


}
