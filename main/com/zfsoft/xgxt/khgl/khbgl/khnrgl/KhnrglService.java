/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khxmgl.KhxmglForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-11 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhnrglService extends SuperServiceImpl<KhnrglForm, KhnrglDao> {
	private static final String SCZT = "1";//1:删除
	private KhnrglDao dao = new KhnrglDao();
	
	public boolean isHave(KhnrglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @描述:考核内容保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editKhnr(KhnrglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xssx=dao.getXssx(model.getKhbid());
			String zbmxid = UniqID.getInstance().getUniqIDHash();
			model.setZbmxid(zbmxid);
			model.setXssx(xssx);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhnr(KhnrglForm t) throws Exception {
		return dao.getKhnr(t);
	}
	public List<HashMap<String, String>> getKhnrList(String khbid)
			throws Exception {
	return dao.getKhnrList(khbid);
	}
	/**
	 * 
	 * @描述:考核内容列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:25:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhnrList() throws Exception {
		return dao.getKhnrList();
	}
	
	public List<HashMap<String, String>> getKhnrList(KhnrglForm model, User user)
	throws Exception{
		return null;
		
	}
	
	

	
	
	



}
