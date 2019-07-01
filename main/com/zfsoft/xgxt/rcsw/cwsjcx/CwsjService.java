/**
 * @部门:学工产品事业部
 * @日期：2013-6-19 下午03:45:29 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(财务数据查询) 
 * @作者： cmj [工号：913]
 * @时间： 2013-6-19 下午03:45:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CwsjService extends SuperServiceImpl<CwsjForm, CwsjDao> {
	private CwsjDao dao=new CwsjDao();
	public CwsjService(){
		super.setDao(dao);
	}
	public List<String[]> getStuCwsjList(String xh){
		return dao.getStuCwsjList(xh);
	}
	
	/**
	 * 
	 * @描述:根据学号查询学生财务数据
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwsjList(String xh) {
		return dao.getCwsjList(xh);
	}

}
