/**
 * @部门:学工产品事业部
 * @日期：2016-1-18 下午06:07:07 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.twsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh.GrzyjwhForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-18 下午06:07:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TwsjService extends SuperServiceImpl<TwsjForm, TwsjDao>{
	TwsjDao dao = new TwsjDao();

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public TwsjService() {
		super.setDao(dao);
	}
	
	//保存
	public boolean saveTwsj(TwsjForm model) throws Exception{
		boolean result = dao.runInsert(model);
		return result;
	}
	
	//修改保存
	public boolean updateTwsj(TwsjForm model) throws Exception {
		boolean updateResult = dao.runUpdate(model);
		return updateResult;
	}
	
	//删除
	public int delTwsj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	//判断记录在该学年是否存在
	public boolean checkExist(TwsjForm form) {
		boolean flag = false;
		if("save".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForSave(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
		else if ("update".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForUpdate(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}		
		return flag;
	}
	
	
	
	
}
