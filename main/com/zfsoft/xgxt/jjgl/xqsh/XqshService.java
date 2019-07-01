/**
 * @部门:学工产品事业部
 * @日期：2014-8-26 下午06:06:30 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-26 下午06:06:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqshService extends SuperServiceImpl<XqshForm, XqshDao> {

	
	/**
	 * 
	 * @描述:审核
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午11:28:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean audit(XqshForm model) throws Exception{
		if(StringUtils.isBlank(model.getXqid())){
			return false;
		}
		
		JjxqService jjxqService = new JjxqService();
		JjxqForm jjxqModel = jjxqService.getModel(model.getXqid());
		if(StringUtils.isNotBlank(model.getShzt())){
			jjxqModel.setShzt(model.getShzt());
			jjxqModel.setZtbz(model.getZtbz());
		}
		return jjxqService.runUpdate(jjxqModel);
	}
	
}
