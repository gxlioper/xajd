/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.dekt.jspj.jspjjg.JspjjgDao;
import com.zfsoft.xgxt.dekt.jspj.jspjjg.JspjjgForm;

public class JspjshService extends SuperServiceImpl<JspjshForm, JspjshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public HashMap<String, String> getJspjxxInfo(JspjshForm model) {
		// TODO Auto-generated method stub
		return dao.getJspjxxInfo(model);
	}

	public boolean saveSh(JspjshForm form, User user) throws Exception {
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
		model.setSqrid(form.getXh());
		model.setTzlj("xg_dekt_jspjglsh.do");
		model.setTzljsq("xg_dekt_jspjglsq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditing(model);
			JspjshForm upForm = new JspjshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				JspjjgForm jspjjgForm = new JspjjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(jspjjgForm, StringUtils.formatData(form));
        		jspjjgForm.setJgid(form.getSqid());
        		jspjjgForm.setSjly("1");
        		jspjjgForm.setSqid(form.getSqid());
        		JspjjgDao jgDao = new JspjjgDao();
        		jgDao.deleteExist(jspjjgForm); //需要删除结果表里重复的数据
        		jgDao.runInsert(jspjjgForm);	
			}	
		return reuslt;
	}

	public boolean CancelSh(JspjshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateJspjxx(model.getSqid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.deleteJspjxxjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}

	public String savePlsh(JspjshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			JspjshForm model = new JspjshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
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

}
