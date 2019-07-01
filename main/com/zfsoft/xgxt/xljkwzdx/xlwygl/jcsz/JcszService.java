/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午08:38:20 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 基础设置
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午08:38:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	public JcszService(){
		setDao(new JcszDao());
	}
	/**
	 * 
	 * @描述:查询配置
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-23 上午08:41:08
	 */
	public JcszForm getJcsz(){
		HashMap<String , String> jcsz = dao.getJcsz();
		if(jcsz.size() == 0)
			return null;
		
		JcszForm model = new JcszForm();
		model.setBjzbrcSplcid(jcsz.get("bjzbrc_splcid"));
		model.setGyzbrcSplcid(jcsz.get("gyzbrc_splcid"));
		model.setPsxxsbSplcid(jcsz.get("psxxsb_splcid"));
		
		return model;
	}
	
	/**
	 * @描述:保存配置
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-23 上午08:47:44
	 */
	public boolean saveJcsz(JcszForm model){
		if(model == null)
			return false;
		
		return dao.saveJcsz(model);
	}
	
}
