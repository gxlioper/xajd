/**
 * @部门:学工产品事业部
 * @日期：2016-1-21 下午02:53:18 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-21 下午02:53:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HqsjService extends SuperServiceImpl<HqsjForm, HqsjDao> {
	HqsjDao dao = new HqsjDao();

	public HqsjService() {
		super.setDao(dao);
	}
	
	//保存
	public boolean saveHqsj(HqsjForm model) throws Exception{
		boolean result = dao.runInsert(model);
		return result;
	}
	
	//修改保存
	public boolean updateHqsj(HqsjForm model) throws Exception{
		boolean updateResult = dao.runUpdate(model);
		return updateResult;
	}
	
	//删除
	public int delHqsj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	//判断记录在该学年该学期是否存在
	public boolean checkExist(HqsjForm form) {
		boolean flag = false;
		if("save".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForSave(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
		else if("update".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForUpdate(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}		
		return flag;
	}
}
