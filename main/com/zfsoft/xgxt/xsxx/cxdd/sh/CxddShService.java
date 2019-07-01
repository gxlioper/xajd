/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:21:50 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgForm;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgService;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgService;
import com.zfsoft.xgxt.zxdk.xnwxdk.sh.XnwxdkShModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-28 下午05:21:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddShService extends SuperServiceImpl<CxddShForm, CxddShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	public List<HashMap<String, String>> getViewList(CxddShForm t, User user) throws Exception{
		return dao.getViewList(t, user);
	}
	
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(CxddShForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getBjid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_cxdd_pysh.do");
		model.setTzljsq("xsxx_cxdd_pysb.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			CxddShForm cxddshform = new CxddShForm();
			cxddshform.setBjid(form.getBjid());
			cxddshform.setShzt(zhzt);
			reuslt = dao.runUpdate(cxddshform, form.getBjid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				this.delJg(form);
				this.insertIntoJg(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean insertIntoJg(CxddShForm t) throws Exception{
		return dao.insertIntoJg(t);
	}
	
	public boolean delJg(CxddShForm t)throws Exception{
		return dao.delJg(t);
	}
	
	//批量审核
	public String savePlsh(CxddShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		CxddShForm model = new CxddShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] bjdms = t.getBjdms();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setBjid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setBjdm(bjdms[i]);
			model.setXn(t.getXn());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(bjdms[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(CxddShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getBjid());
		if (result) {
		
			// 删除结果表中的申请结果
			 this.delJg(myForm);
		
		}
		return result;
	}

	public String cxshnew(String ywid, CxddShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		this.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception {
		return dao.updateSqjl(ywid, shzt);
	}
}
