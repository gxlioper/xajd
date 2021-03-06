/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午03:16:14 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午03:16:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SfbzService extends SuperServiceImpl<SfbzForm, SfbzDao> {

	/**
	 * 
	 * @描述:新建
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 上午09:26:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean add(SfbzForm model) throws Exception{
		boolean isSuccess = false;
		//check
		SfbzForm curData = dao.getModelByXkdmAndNjdm(model.getJjxkdm(), model.getJjnjdm());
		if(curData == null){
			model.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
			isSuccess = dao.runInsert(model);
		}
		return isSuccess;
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午04:46:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jjnjdm
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public boolean deleteSfbzByJJnjdm(String jjnjdm) throws Exception{
		return dao.deleteSfbzByJJnjdm(jjnjdm) > 0;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午04:46:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jjnjdm
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public boolean deleteSfbzByJJxkdm(String jjxkdm) throws Exception{
		return dao.deleteSfbzByJJxkdm(jjxkdm) > 0;
	}
}
