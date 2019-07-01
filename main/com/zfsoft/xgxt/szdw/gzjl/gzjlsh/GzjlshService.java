/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:34:13 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgDao;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqDao;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqForm;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:34:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlshService extends SuperServiceImpl<GzjlshForm, GzjlshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	private GzjlshDao dao = new GzjlshDao();
	private GzjljgDao gzjljgDao = new GzjljgDao();
	
	public HashMap<String, String> getGzjlshInfo(GzjlshForm t) {
		return dao.getGzjlshInfo(t);
	}
	public boolean saveSh(GzjlshForm form, User user) {
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
		model.setSqrid(form.getZgh());
		model.setTzlj("qgzx_mrgzkh_khsh.do");
		model.setTzljsq("qgzx_mrgzkh_khsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			GzjlshForm gzjlshForm = new GzjlshForm();
			gzjlshForm.setSqid(form.getSqid());
			gzjlshForm.setShzt(zhzt);
			
			reuslt = dao.runUpdate(gzjlshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				GzjljgForm gzjljgForm = new GzjljgForm();
				GzjlsqForm khsqForm = new GzjlsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(gzjljgForm, StringUtils.formatData(khsqForm));
				gzjljgForm.setJgid(khsqForm.getSqid());
				gzjljgForm.setSqid(khsqForm.getSqid());
				gzjljgForm.setSjly("1");
				reuslt = gzjljgDao.runInsert(gzjljgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public String savePlsh(GzjlshForm t, User user) throws Exception {
		GzjlshForm model = new GzjlshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] zghs = t.getZghs();
		List<String> failzghs = new ArrayList<String>();
		
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setZgh(zghs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failzghs.add(zghs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failzghs);
		if (failzghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	/**
	 * 
	 * @描述:最后一级审核撤销
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 上午09:07:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(GzjlshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// 删除结果库中的数据
		result=gzjljgDao.delJgById(myForm.getSqid());
		return result;
	}

}
