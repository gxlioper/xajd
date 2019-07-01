/**
 * @部门:学工产品事业部
 * @日期：2014-2-18 下午04:43:06 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import net.sf.json.JSONArray;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2014-2-18 下午04:43:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaxxglService extends SuperServiceImpl<DaxxglForm,DaxxglDao> {
	private DaxxglDao dao = new DaxxglDao();
	
	public DaxxglService(){
		super.setDao(dao);
	}
	
	public String getXsszInfo() throws Exception{
		return dao.getXsszInfo();
	}
	public List<HashMap<String, String>> getByqxList() throws Exception{
		return dao.getByqxList();
	}
	public HashMap<String, String> getDaxxInfoByPk(String pk) throws Exception{
		return dao.getDaxxInfoByPk(pk);
	}
	public HashMap<String, String> getDaxxTableByPk(String pk) throws Exception{
		return dao.getDaxxTableByPk(pk);
	}
	
	//学生模板内的材料List
	public List<HashMap<String, String>> getXsdaclListByBmid(String daqdmb_id,String pk) throws Exception{
		return dao.getXsdaclListByBmid(daqdmb_id,pk);
	}
	
	//学生模板外的材料List
	public List<HashMap<String, String>> getXsMbwclListByBmid(String daqdmb_id,String pk) throws Exception{
		return dao.getXsMbwclListByBmid(daqdmb_id,pk);
	}
	
	public boolean updateDaxxgl(DaxxglForm form) throws Exception{
		return dao.updateDaxxgl(form);
	}
	
	public boolean updateDaxxgl(String daqdmb_id,String pk) throws Exception{
		return dao.updateDaxxgl(daqdmb_id, pk);
	}
	
	//删除档案信息，并删除学生材料信息
	public int delDaxxgl(String[] pk) throws Exception{
		int daxxCount = dao.delDaxxgl(pk);
		int xsclCount = dao.delXscl(pk);
		return daxxCount;
	}
	
	public boolean saveXsdaxxBand(String xh,String dazrsj,JSONArray jsonArray) throws Exception{
		return dao.saveXsdaxxBand(xh,dazrsj,jsonArray);
	}
	
	public boolean saveBatchXsdaxxBand(String[] pk,JSONArray jsonArray) throws Exception{
		return dao.saveBatchXsdaxxBand(pk, jsonArray);
	}
	

	/** 
	 * @描述:取得档案维护状态统计
	 * @日期：2014-4-25 下午02:56:20
	 * @param myForm
	 * @param user
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDabdxx(DaxxglForm model, User user) throws Exception{
		return dao.getDabdxx(model, user);
	}


	/**  批量更新提交档案材料信息
	 * @日期：2014-4-28 下午02:31:55
	 * @param daqdmbId
	 * @param jsonStr
	 * @param myForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateBatchDaxxgl(String daqdmbId, JSONArray jsonArray,
			DaxxglForm model, User user) throws Exception{

		boolean bolFlg = dao.updateBatchDaxxgl(daqdmbId, jsonArray, model , user);		
		return bolFlg;
	}
}
