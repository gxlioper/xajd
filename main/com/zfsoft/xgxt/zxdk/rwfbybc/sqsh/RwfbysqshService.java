/**
 * @部门:学工产品事业部
 * @日期：2015-9-7 下午05:14:06 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgDao;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-9-7 下午05:14:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwfbysqshService extends SuperAuditService<RwfbysqshModel, RwfbysqshDao> {
	private static final String SQSH = "1";
	
	@Override
	public boolean afterLastAudit(RwfbysqshModel model) {
		
		try {
			RwfbydcjgModel jgModel = new RwfbydcjgModel();
			BeanUtils.copyProperties(jgModel, model);
			RwfbydcjgDao jgDao = new RwfbydcjgDao();
			boolean flag = jgDao.isExists(jgModel);
			if(flag){
				jgDao.deleteByKey(jgModel.getXh());
			}
			dao.runUpdate(model);
			jgModel.setDclb(model.getZd2());
			jgModel.setDcje(model.getZd6());
			jgModel.setSjly(SQSH);
			return jgDao.runInsert(jgModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean deleteResult(RwfbysqshModel model) {
		try {
			return new RwfbydcjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public List<HashMap<String, String>> getAudingList(RwfbysqshModel model,User user) throws Exception{
		return dao.getAudingList(model,user);
	}
	
	/**
	 * 
	 * @描述:验证是否已申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-7 下午06:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists(RwfbysqshModel model) {	
		return dao.isExists(model);
	}


	/** 
	 * @描述:根据申请学号学年获取生源地贷款信息(苏州卫生)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-16 下午02:46:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSyddkxx(String id) {
		return dao.getSyddkxx(id);
	}


	/** 
	 * @描述:根据申请学号，学年获取校内贷款信息(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-16 下午02:54:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXyddkxx(String id) {
		return dao.getXyddkxx(id);
	}
}
