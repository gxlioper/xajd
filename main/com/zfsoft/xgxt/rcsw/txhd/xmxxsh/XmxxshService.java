package com.zfsoft.xgxt.rcsw.txhd.xmxxsh;

import java.util.ArrayList;
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
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;

public class XmxxshService extends SuperServiceImpl<XmxxshForm, XmxxshDao> {

	private XmxxshDao dao = new XmxxshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XmxxshService() {
		super.setDao(dao);
	}
	
	/**
	 * 保存
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(XmxxshForm form,User user){
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
		model.setSqrid(form.getSqr());
		// ======= 业务字段 end======
		model.setTzlj("rcsw_txhd_xmxxsh.do");
		model.setTzljsq("rcsw_txhd_xmxxsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XmxxshForm upForm = new XmxxshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				TxhdXmszForm txhdjgForm = new TxhdXmszForm();
				form = getModel(form);
        		BeanUtils.copyProperties(txhdjgForm, StringUtils.formatData(form));
        		txhdjgForm.setSjly("1");
        		txhdjgForm.setXmdm(form.getSqid());
        		TxhdXmszDao txhdjgDao = new TxhdXmszDao();
        		txhdjgDao.runInsert(txhdjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/** 
	 * @描述:批量审核保存
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(XmxxshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= 业务字段 begin======
		String[] sqrs = t.getSqrs();
		// ======= 业务字段 end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XmxxshForm model = new XmxxshForm();
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= 业务字段 begin======
			model.setSqr(sqrs[i]);
			// ======= 业务字段 end======
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
//				fails.add(sqrs[i]);
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
	 * @描述:撤销项目审核
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(XmxxshForm model, User user){
		boolean resultTxhd = false;
		try {
			model = getModel(model);
			//更新申请表
			resultTxhd = dao.updateXmxxsq(model.getSqid(), Constants.YW_SHZ);
			if(resultTxhd && Constants.YW_TG.equals(model.getShzt())){
				//删除结果表
				resultTxhd = dao.deleteTxhdjg(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultTxhd;
	}

}
