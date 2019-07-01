/**
 * @部门:学工产品（1）部
 * @日期：2017-7-7 上午09:50:25 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.cprytzjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 参评人员调整记录 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-7 上午09:50:08 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CprytzjlService extends SuperServiceImpl<CprytzjlForm,CprytzjlDao>{
	
	private CprytzjlDao dao = new CprytzjlDao();
	public CprytzjlService(){
		super.setDao(dao);
	}

}
