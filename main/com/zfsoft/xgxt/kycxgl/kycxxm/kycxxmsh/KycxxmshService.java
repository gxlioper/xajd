package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg.KycxxmjgDao;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg.KycxxmjgForm;

public class KycxxmshService extends SuperServiceImpl<KycxxmshForm, KycxxmshDao> {

	private KycxxmshDao dao = new KycxxmshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public KycxxmshService() {
		super.setDao(dao);
	}
	/**
	 * 保存
	 */
	public boolean saveSh(KycxxmshForm form,User user){
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
		// ======= 业务字段 begin======
		model.setSqrid(form.getCzr());
		model.setZd1("下拨经费（元）");
		model.setZd3(form.getXbjf());
		// ======= 业务字段 end======
		String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
		String shURL = "kycxgl_kycxxm_kycxxmsh.do";
		model.setTzlj(shURL);
		model.setTzljsq(sqURL);
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			KycxxmshForm upForm = new KycxxmshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				String xbjf = form.getXbjf();
				KycxxmjgForm kycxxmjgForm = new KycxxmjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(kycxxmjgForm, StringUtils.formatData(form));
        		kycxxmjgForm.setSjly("1");
        		kycxxmjgForm.setXbjf(xbjf);
        		kycxxmjgForm.setJgid(form.getSqid()); //将申请id作为结果id，可以公用项目成员数据
        		KycxxmjgDao kycxxmjgDao = new KycxxmjgDao();
        		kycxxmjgDao.runInsert(kycxxmjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	/** 
	 * 批量审核保存
	 */
	public String savePlsh(KycxxmshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= 业务字段 begin======
		String[] czrs = t.getCzrs();
		// ======= 业务字段 end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			KycxxmshForm model = new KycxxmshForm();
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= 业务字段 begin======
			model.setCzr(czrs[i]);
			model.setXbjf(t.getXbjf());
			// ======= 业务字段 end======
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				fails.add(czrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(fails);
		if (fails.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	/**
	 * 撤销科研创新项目审核
	 */
	public boolean newCancelSh(KycxxmshForm model, User user){
		boolean result = false;
		try {
			//更新申请表
			result = dao.updateKycxxmsqShzt(model.getSqid(), Constants.YW_SHZ);
			if(result){
				//删除结果表
				result = dao.deleteKycxxmjg(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
