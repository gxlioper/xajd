/**
 * @部门:学工产品事业部
 * @日期：2017-1-3 下午02:47:26 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rwdjsh.RwdjshForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-3 下午02:47:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwdjsqService extends SuperServiceImpl<RwdjsqForm, RwdjsqDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述: 验证该学生已存在入伍登记记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午09:01:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		return dao.checkIsNotExist(xh);
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午09:17:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(RwdjsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getRwdjid(), model.getSplc(), model.getXh(), "gygl_xyzssh.do", "gygl_xyzssq.do");
		}
		return flag;
	}
	
	//获取审批流
	public String getShlcID() {
		return dao.getShlcID();
	}
	
	//获取spid
	public String getSqbh(RwdjsqForm model) {
		return dao.getSqbh(model);
	}
	
	//获取审核状态名称
	public String getShztMc(RwdjsqForm model) {
		return dao.getShztMc(model);
	}
	
	/**
	 * 
	 * @描述: 保存增加申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 下午02:42:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJg(RwdjsqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("savesubmit")){
			if (flag) {
				String sqbh = dao.getSqbh(model);
				flag = shlc.runSubmit(sqbh, splc, model.getXh(), "rwdjshbase.do", "rwdjsqbase.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 保存修改申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 下午02:48:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveUpdateJg(RwdjsqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("savesubmit")){
			if (flag) {
				String sqbh = dao.getSqbh(model);
				flag = shlc.runSubmit(sqbh, splc, model.getXh(), "rwdjshbasel.do", "rwdjsqbase.do");
			}
		}
		return flag;
	}
	
	@Override
	public RwdjsqForm getModel(RwdjsqForm t) throws Exception {
		return dao.getModel(t);
	}
}
