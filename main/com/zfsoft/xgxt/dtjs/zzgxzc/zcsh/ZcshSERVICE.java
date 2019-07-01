/**
 * @部门:学工产品事业部
 * @日期：2017-2-10 下午04:55:48 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-10 下午04:55:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcshSERVICE extends SuperServiceImpl<ZcshForm,ZcshDAO> {
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(ZcshForm form, User user) throws Exception {
		//当审核结果为通过时，更新sqb字段
		if("1".equals(form.getShjg())){
			dao.runUpdateNotCommit(form);
		}
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dtjs_dzzgxsh.do");
		model.setTzljsq("dtjs_dzzgxsq.do");
		boolean reuslt = false;
		String zhzt = shlc.runAuditingNotCommit(model);
		ZcshForm  shForm = new ZcshForm();
		shForm.setSqid(form.getSqid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
		// 保存到结果表
		if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
			ZcshForm zcshForm = this.getModel(form);
			form.setSqsj(zcshForm.getSqsj());
			form.setSqr(zcshForm.getSqr());
			//最后一级通过，要验证填写的介绍信编号是否已存在结果表中
			if(!dao.isNotJsxInJgb(form.getJsxbh())){
				throw new SystemException(MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT);
			}
			if(!dao.isNotExistInjgb(form.getXh())){
				dao.delJgByXh(form.getXh());
			}
			reuslt = dao.saveZcJg(form);
		}
		return reuslt;
	}
	
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:06:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(ZcshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			// 删除结果表中的申请结果
			dao.delJg(myForm.getSqid());
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:21:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param shForm
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, ZcshForm shForm, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		shForm.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), shForm.getShid(), shForm.getShlc());
		dao.runUpdate(shForm, shForm.getSqid());
		return cancelFlag;

	}


	/** 
	 * @描述:判断是否显示介绍信编号，审核岗位为非最后一级显示，如果审核流程只经一个岗位，则第一级显示
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月14日 上午10:35:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isJsxbhShow(ZcshForm zcshform) {
		boolean r = false;
		List<HashMap<String,String>> splcgwList = dao.getSplcgwList(zcshform.getSplcid());
		if(splcgwList.size()==1){
			r = true;
		}else{
			String spgw=splcgwList.get(0).get("spgw");
			r = !zcshform.getGwid().equalsIgnoreCase(spgw);
		}
		return r;
	}
}
