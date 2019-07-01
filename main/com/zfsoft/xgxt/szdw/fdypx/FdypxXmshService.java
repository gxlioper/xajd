/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:14:25 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训审核
 * @作者： zhangjw
 * @时间： 2013-7-25 下午4:14:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class FdypxXmshService extends SuperServiceImpl<FdypxXmshForm, FdypxXmshDAO> {
	
	private FdypxXmshDAO dao = new FdypxXmshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public FdypxXmshService() {
		// TODO 自动生成方法存根
		super.setDao(dao);
	}
	/**
	 * @描述:辅导员培训审核
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:10:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean fdypxsh(FdypxXmshForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getSqid());
		model.setThgw(form.getThgw());
		
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getFbr());
		model.setTzlj("szdw_fdypxxmsh.do?method=fdypxxmList");
		model.setTzljsq("szdw_fdypxxmsq.do?method=fdypxxmsqList");
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			FdypxXmshForm upForm = new FdypxXmshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			if(zhzt.equals(Constants.OPEN)){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean newCancelSh(FdypxXmshForm model){
		boolean resultFdyrzsq = false;
		try {
			model.setShzt(Constants.YW_SHZ);
			//更新日常行为信息维护
			resultFdyrzsq = dao.updateFdyrzsq(model)>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFdyrzsq;
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:批量保存审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-29 下午04:06:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(FdypxXmshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			FdypxXmshForm model = new FdypxXmshForm();
			model.setSplc(splcs[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setFbr(xhs[i]);
			
			boolean isSuccess = fdypxsh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
		
	}
	
	/**
	 * 
	 * @描述:获得结果信息，不包含自己
	 * @作者：cq [工号：785]
	 * @日期：2015-4-24 下午05:05:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqr
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqjg(String sqid){
		return dao.getSqjg(sqid);
	}
	
}
