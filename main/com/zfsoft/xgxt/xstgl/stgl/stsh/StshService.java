/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:46 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgDao;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgForm;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqDao;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4 下午02:38:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StshService extends SuperServiceImpl<StshForm, StshDao>{
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private ShlcInterface shlc = new CommShlcImpl();
	private StshDao dao = new StshDao();
	private StsqDao sqDao = new StsqDao();
	private StsqService stsqService = new StsqService();
	/**
	 * 
	 * @描述:加载审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 上午10:40:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String Stid) {
		return dao.getXmshInfo(Stid);

	}
	/**
	 * 
	 * @描述:审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午02:35:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(StshForm form, User user) {
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
		model.setYwid(form.getSqid());
		model.setSqrid(user.getUserName());
		model.setTzlj("stgl_stgl_stsh.do");
		model.setTzljsq("stgl_stgl_stsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			StshForm stshForm = new StshForm();
			stshForm.setSqid(form.getSqid());
			stshForm.setShzt(zhzt);
			result = dao.runUpdate(stshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				StjgForm stjgForm = new StjgForm();
				StjgService stjgService = new StjgService();
				StsqForm sqForm = new StsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(stjgForm, StringUtils.formatData(sqForm));
				stjgForm.setStid(sqForm.getSqid());
				stjgForm.setSjly("1");
				//版本升级后修改，走审批流过来的数据，当审核通过插入数据表时，将审批流程（splc）内置为空
				stjgForm.setSplc(null);
				stjgService.runInsert(stjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午02:35:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(StshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			StshForm model = new StshForm();
			StsqForm stsq = stsqService.getModel(ids[i]);
		//	Map<String, String> result = sqDao.getSqxx(ids[i]);
			model.setSplc(stsq.getSplc());
			model.setYwid(ids[i]);
			model.setSqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @描述:最后一级撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午01:59:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(StshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// 删除结果库中的数据
			StjgDao stjgDao = new StjgDao();
			result = stjgDao.delStjg(myForm.getSqid());
		return result;
	}



}
