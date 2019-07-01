/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 上午08:39:25 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgDao;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgService;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqDao;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqForm;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqService;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgDao;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.tzxmsh.XsXmShForm;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqDao;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-8-14 上午08:39:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtshService extends SuperServiceImpl<RtshForm, RtshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private RtjgService rtjgService = new RtjgService();
	private RtsqDao rtsqDao = new RtsqDao();
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(RtshForm form, User user) throws Exception {
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
		model.setYwid(form.getRtid());
		model.setSqrid(form.getXh());
		model.setTzlj("stgl_rtgl_rtsh.do");
		model.setTzljsq("stgl_rtgl_rtsq.do");
		boolean reuslt = false;
		String flag = "false";
		String zhzt = shlc.runAuditing(model);
		form.setShzt(zhzt);
		reuslt = dao.runUpdate(form, form.getRtid());
		// 保存到结果表
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt == true) {
			RtjgForm rtjgform = new RtjgForm();
			RtsqForm rtsqForm = rtsqDao.getModel(form.getRtid());
			BeanUtils.copyProperties(rtjgform, StringUtils.formatData(rtsqForm));
			String ids = dao.checkExistForSave2(rtjgform.getXh(),rtjgform.getStid());
			if(!ids.equals("") && ids != null){
				rtjgService.runDelete(new String[]{ids});
			}
			rtjgform.setSjly("1");
			rtjgform.setTnzt("正常");
			reuslt = rtjgService.runInsert(rtjgform);
			if(reuslt){
				reuslt = rtjgService.saveAddRtCySl(rtjgform.getStid(), "1");
			}
		}
	
		return reuslt;
	}
	
	
	//批量审核
	public String savePlsh(RtshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		RtshForm model = new RtshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] stids = t.getStids();
		List<String> failXhs = new ArrayList<String>();
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(splcs[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRtid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setStid(stids[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
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
	
	public boolean cancel(RtshForm myForm) throws Exception {
		String flag = myForm.getShzt();
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getRtid());
		if (result&&flag.equals(Constants.YW_TG)) {
			RtjgDao jgdao = new RtjgDao();
			result = jgdao.runDelete(new String[]{myForm.getRtid()})>0;
			if(result){
				rtjgService.saveDelRtCySl(myForm.getStid(), "1");
			}
		
		}
		return result;
	}

	public String cxshnew(String ywid, RtshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}

}
