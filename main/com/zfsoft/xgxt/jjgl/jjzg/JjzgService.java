/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 上午11:19:22 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;

/** 
 * @类功能描述: 家教资格 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 上午11:19:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjzgService extends SuperAuditService<JjzgForm, JjzgDao> {

	private static final String default_rddj = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(JjzgForm model) {
		
		model.setRddj(default_rddj);
		
		try {
			return dao.runUpdate(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(JjzgForm model) {
		return false;
	}

	
	/**
	 * 
	 * @描述:查询家教老师信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午12:42:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String xh) throws Exception{
		return dao.getModelMap(xh);
		
	}
	
	/**
	 * 
	 * @描述:获取家教经历列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-2 下午03:01:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getJJjlList(String xh) throws Exception{
		return dao.getJJjlList(xh);
	}
	
	
}
