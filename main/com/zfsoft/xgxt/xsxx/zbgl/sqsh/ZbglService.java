/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.zbgl.zbjg.ZbjgDao;
import com.zfsoft.xgxt.xsxx.zbgl.zbjg.ZbjgModel;

/**
 * 
 * @类功能描述: 征兵管理 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-3-18 下午01:58:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbglService extends SuperAuditService<ZbglModel, ZbglDao> {

	
	private static final String SQSH = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(ZbglModel model) {
		
		
		try {
			dao.runUpdate(model);
			
			ZbjgModel zbjgModel = new ZbjgModel();
			BeanUtils.copyProperties(zbjgModel, model);
			zbjgModel.setSjly(SQSH);
			
			return new ZbjgDao().runInsert(zbjgModel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(ZbglModel model) {
		try {
			return new ZbjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(ZbglModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

}
