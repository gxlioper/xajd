/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:35:25 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgDao;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:35:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkbcService extends SuperAuditService<DkbcModel, DkbcDao> {

	private static final String SQSH = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DkbcModel model) {
		
		BcjgModel jdqk = new BcjgModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(jdqk, model);
			BcjgDao bcjgDao = new BcjgDao();
			String count = bcjgDao.getCountByXhAndXn(jdqk);
			if(Integer.parseInt(count)>0){
				bcjgDao.deleteByKey(jdqk.getXh());
			}
			jdqk.setDclb(model.getZd2());
			jdqk.setDcje(model.getZd6());
			jdqk.setSjly(SQSH);
			return bcjgDao.runInsert(jdqk);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DkbcModel model) {
		try {
			return new BcjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(DkbcModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

	/**按学号学年查询是否有申请 */
	public String getCountByXhAndXn(DkbcModel djjdForm) {
		
		return dao.getCountByXhAndXn(djjdForm);
	}
	
	/**
	 * 
	 * @描述:获取银行List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		return dao.getYhList();
	}
	
	/**
	 * 
	 * @描述:获取代偿类别List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDclbList(){
		return dao.getDclbList();
	}
	

	/**
	 * 
	 * @描述:获取学费List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfList(){
		return dao.getXfList();
	}
	
	//华中师范大学贷款类型联动查询助学贷款结果表或者生源地贷款结果表的数据
	public HashMap<String, String> getHzsfDxDklxChange(String xh,String dklx,String xn){
		return dao.getHzsfDxDklxChange(xh, dklx, xn);
	}
	
	public HashMap<String, String> produceDataMap(String xh,String dklx,String xn){
		HashMap<String, String> datamap = this.getHzsfDxDklxChange(xh, dklx, xn);
		if(datamap != null){
			String dkkssj = datamap.get("dkkssj");
			String dkjssj = "";
			String dkqx = datamap.get("dkqx");
			String n = datamap.get("n");
			String y = datamap.get("y");
			String d = datamap.get("d");
			if(StringUtils.isNotNull(dkkssj) && StringUtils.isNotNull(dkqx) && dkkssj.length() == 10 && dkkssj.indexOf("-")!=-1){
				dkjssj = this.produceDkjssj(dkkssj, dkqx, dklx);
			}else{
				datamap.put("dkjssj", "");
				datamap.put("dkkssj", "");
			}
		}
		
		return datamap;
	}
	
	public String produceDkjssj(String dkkssj,String dkqx,String dklx){
		
		return dao.produceDkjssj(dkkssj, dkqx, dklx);
	}
}
