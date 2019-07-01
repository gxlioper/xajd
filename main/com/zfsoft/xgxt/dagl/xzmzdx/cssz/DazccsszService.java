/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:37:04 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理管理模块
 * @类功能描述: 档案转出参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:37:31 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazccsszService extends SuperServiceImpl<DazccsszForm,DazccsszDao>{
	
	private DazccsszDao dao = new DazccsszDao();
	public DazccsszService(){
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public DazccsszForm getModel(DazccsszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 查询基础设置信息(无参数)
	 */
	public DazccsszForm getModel()throws Exception{
		return getModel(new DazccsszForm());
	}
	
	/**
	 * 参数设置保存
	 */
	public boolean dazccsszSave(DazccsszForm model) throws Exception{
		boolean flag = false;
		flag = dao.deleteTableName();
		if(flag){
			flag = dao.runInsert(model);
		}
		return flag;
	}
}
