/**
 * @部门:学工产品事业部
 * @日期：2016年12月27日 上午11:38:27 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-资助款发放管理模块
 * @类功能描述: 学生资助Service
 * @作者： xuwen[工号:1426]
 * @时间： 2016年12月27日 上午11:38:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzkffService extends SuperServiceImpl<ZzkffForm, ZzkffDao>{
	
	private ZzkffDao zzkffDao = new ZzkffDao();
	
	public ZzkffService(){
		super.setDao(zzkffDao);
	}

	/**
	 * @描述:判断该学生在同学年学期是否有同项目名称资助款
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月3日 下午4:33:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zzkffForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean IsXmmcRepeat(ZzkffForm zzkffForm){
		return zzkffDao.IsXmmcRepeat(zzkffForm);
	}
	
	/**
	 * @描述:重载getModel
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月28日 下午5:59:05
	 * @throws Exception 
	 */
	public ZzkffForm getModel(String id) throws Exception{
		return zzkffDao.getModel(id);
	}
}
