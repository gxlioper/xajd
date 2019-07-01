/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:31:43 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 提前还款 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-5 上午09:31:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TqhkService extends SuperAuditService<TqhkModel, TqhkDao> {

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(TqhkModel model) {
		
		HkjgModel hkjgModel = new HkjgModel();

		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(hkjgModel, model);
			hkjgModel.setHkzt(model.getZd2());
			return new HkjgDao().runInsert(hkjgModel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(TqhkModel model) {
		try {
			return new HkjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public List<HashMap<String, String>> getAudingList(TqhkModel t, User user)
		throws Exception {
		return dao.getAudingList(t, user);
	}

}
