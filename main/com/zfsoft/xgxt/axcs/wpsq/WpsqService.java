/**
 * @部门:学工产品事业部
 * @日期：2014-12-5 下午02:21:29 
 */  
package com.zfsoft.xgxt.axcs.wpsq;

import java.util.ArrayList;

import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-5 下午02:21:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WpsqService extends SuperServiceImpl<WpsqForm,WpsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	WpsqDao dao  = new WpsqDao();
	
	public ArrayList<String[]> getWpsqList(WpszForm t) throws Exception{
		return dao.getWpsqList(t);
		
	}
	
	/**
	 * 
	 * @描述:学生申请物品保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:44:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean wpsqBc(String[] xmdm, WpsqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String wp : xmdm) {
			WpsqForm model = new WpsqForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(wp);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			saveWpsq(model, t.getXh());
		}
		return true;
	}
	/**
	 * 
	 * @描述:物品申请单个保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:40:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWpsq(WpsqForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		WpszDao wpszDao = new WpszDao();
		WpszForm wpszForm = wpszDao.getModel(model.getXmdm());
		String splc = wpszForm.getSplc();
		model.setSqr(userName);
		model.setXn(wpszForm.getXn());
		model.setSqid(sqid);
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);

		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(),"axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		}
		return result;
	}
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午01:48:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	

}
