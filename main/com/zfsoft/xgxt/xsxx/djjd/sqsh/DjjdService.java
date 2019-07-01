/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.djjd.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.djjd.jdqk.JdqkDao;
import com.zfsoft.xgxt.xsxx.djjd.jdqk.JdqkModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:35:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DjjdService extends SuperAuditService<DjjdModel, DjjdDao> {

	private static final String SQSH = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DjjdModel model) {
		
		JdqkModel jdqk = new JdqkModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(jdqk, model);
			jdqk.setSjly(SQSH);
			return new JdqkDao().runInsert(jdqk);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DjjdModel model) {
		try {
			return new JdqkDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(DjjdModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

}
