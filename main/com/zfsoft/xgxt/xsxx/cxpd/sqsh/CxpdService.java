/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyDao;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 操行评定-申请审核 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2015-1-14 下午03:55:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CxpdService extends SuperAuditService<CxpdModel, CxpdDao> {

	private static final String SQSH = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(CxpdModel model) {
		
		CxpyForm cxpyModel = new CxpyForm();
		
		cxpyModel.setXh(model.getXh());
		cxpyModel.setXn(model.getXn());
		cxpyModel.setXq(model.getXq());
		cxpyModel.setCxdj(model.getCxdj());
		cxpyModel.setCxpy(model.getCxpy());
		cxpyModel.setBzr(model.getBzr());
		if("13943".equalsIgnoreCase(Base.xxdm)) {
			cxpyModel.setSqsj(model.getSqsj());
		}
		cxpyModel.setSjly(SQSH);
		
		try {
			
			dao.runUpdate(model);
			
			CxpyDao cxpyDao = new CxpyDao();
			String count = cxpyDao.getCount(cxpyModel);
			
			if (Integer.valueOf(count) == 0){
				cxpyModel.setPk(model.getId());
				return cxpyDao.runInsert(cxpyModel);
			} else {
				return cxpyDao.updCxpy(cxpyModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(CxpdModel model) {
		try {
			return new CxpyDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(CxpdModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

	
	public String getCount(CxpdModel t){
		return dao.getCount(t);
	}
	
}
