/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:35:25 
 */  
package com.zfsoft.xgxt.ybgzz.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.ybgzz.cywh.YbcyDao;
import com.zfsoft.xgxt.ybgzz.cywh.YbcyModel;

/**
 * 
 * @类功能描述: 易班工作站 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-29 上午09:09:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbgzzService extends SuperAuditService<YbgzzModel, YbgzzDao> {

	private static final String SQSH = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(YbgzzModel model) {
		
		YbcyModel ybcy = new YbcyModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(ybcy, model);
			ybcy.setSjly(SQSH);
			
			YbcyDao ybcyDao = new YbcyDao();
			String count = ybcyDao.getExistsByXh(model.getXh());
			
			if (Integer.valueOf(count) == 0){
				return ybcyDao.runInsert(ybcy);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(YbgzzModel model) {
		try {
			return new YbcyDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(YbgzzModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

}
