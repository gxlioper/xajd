/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglDao;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglForm;

public class StglshService extends SuperServiceImpl<StglshForm, StglshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public HashMap<String, String> getStxxInfo(StglshForm model) {
		return dao.getStxxInfo(model);
	}

	public boolean saveSh(StglshForm form, User user) throws Exception {
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
		model.setTzlj("xg_ttgl_stglsh.do");
		model.setTzljsq("xg_ttgl_stglsq.do");
		boolean reuslt = false;
			String zhzt = shlc.runAuditing(model);
			StglshForm upForm = new StglshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				StglForm stglForm = new StglForm();
				form = getModel(form);
        		BeanUtils.copyProperties(stglForm, StringUtils.formatData(form));
        		stglForm.setJgid(form.getSqid());
        		stglForm.setSjly("1");
        		stglForm.setStzt("0");//流程数据的社团状态都为0：预备期
        		stglForm.setSqid(form.getSqid());
        		StglDao jgDao = new StglDao();
        		jgDao.deleteExist(stglForm); //需要删除结果表里重复的数据
        		jgDao.runInsert(stglForm);	
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		dao.updateStcyb(form.getSqid(),df.format(new Date()));//更新成员表里的审核状态
			}	
		
		return reuslt;
	}

	public boolean checkisCancel(StglshForm t) throws Exception{
		return dao.checkisCancel(t);
	}

	public boolean CancelSh(StglshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateStsq(model.getSqid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.deleteStxxjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}

	public String savePlsh(StglshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] zzids = t.getZzid();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] shlxs = t.getShlxs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			StglshForm model = new StglshForm();
			model.setSplc(splcs[i]);
			model.setShlx(shlxs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setZzywid(zzids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			boolean isSuccess =true;
			if ("0".equals(model.getShlx())) {
				 isSuccess = saveSh(model, user);
			}else {
				 isSuccess = saveZzSh(model, user);
			}
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

	public boolean saveZzSh(StglshForm form, User user) throws Exception {
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
		model.setYwid(form.getZzywid());
		model.setSqrid(form.getXh());
		model.setTzlj("xg_ttgl_stglsh.do");
		model.setTzljsq("xg_ttgl_stglsq.do");
		boolean reuslt = false;
			String zhzt = shlc.runAuditing(model);
			StglshForm upForm = new StglshForm();
			upForm.setZzywid(form.getZzywid());
			upForm.setZzshzt(zhzt);
			reuslt = dao.updateZzshzt(upForm);//更新转正审核状态
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				//更新结果表里的社团状态为正式
				reuslt = dao.updateJgbStzt(form);
			}
		return reuslt;
	}

	public boolean CancelZzSh(StglshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateSqbZzshzt(model.getZzywid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.updateStzt(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}


}
