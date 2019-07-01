package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
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
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg.XsgzzbjgDao;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg.XsgzzbjgForm;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;

public class XsgzzbshService extends SuperServiceImpl<XsgzzbshForm, XsgzzbshDao> {

	private XsgzzbshDao dao = new XsgzzbshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XsgzzbshService() {
		super.setDao(dao);
	}
	
	/**
	 * 班级周报
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbshForm t, User user)
		throws Exception {
		return dao.getPageListBj(t, user);
	}
	
	@Override
	public List<HashMap<String, String>> getAllList(XsgzzbshForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		String gzzblx = t.getGzzblx();
		if("bj".equals(gzzblx)){
			return dao.getPageListBj(t, user);
		}
		return dao.getPageList(t, user);
	}

	/**
	 * 班级周报
	 */
	public XsgzzbshForm getModelBj(XsgzzbshForm t) throws Exception{
		return dao.getModelBj(t);
	}
	
	/**
	 * 保存
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(XsgzzbshForm form,User user){
		String gzzblx = form.getGzzblx();
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
		model.setSqrid(form.getLrr());
		// ======= 业务字段 end======
		String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
		String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
		if("bj".equals(gzzblx)){
			sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
			shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
		}
		model.setTzlj(shURL);
		model.setTzljsq(sqURL);
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XsgzzbshForm upForm = new XsgzzbshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			dao.setTableInfo(form);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XsgzzbjgForm xsgzzbjgForm = new XsgzzbjgForm();
				if("bj".equals(gzzblx)){
					form = getModelBj(form);
				}else{
					form = getModel(form);
				}
        		BeanUtils.copyProperties(xsgzzbjgForm, StringUtils.formatData(form));
        		xsgzzbjgForm.setSjly("1");
        		XsgzzbjgDao xsgzzbjgDao = new XsgzzbjgDao();
        		xsgzzbjgForm.setGzzblx(gzzblx);
        		XsgzzbsqService xsgzzbsq = new XsgzzbsqService();
        		if(Base.xxdm.equalsIgnoreCase("13815")){
        			String filegid = xsgzzbsq.getFileGID(xsgzzbjgForm.getSqid());
            		xsgzzbjgForm.setFilegid(filegid);
        		}
        		xsgzzbjgDao.setTableInfo(xsgzzbjgForm);
        		xsgzzbjgDao.runInsert(xsgzzbjgForm);	
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
	public String savePlsh(XsgzzbshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= 业务字段 begin======
		String[] lrrs = t.getLrrs();
		// ======= 业务字段 end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XsgzzbshForm model = new XsgzzbshForm();
			model.setGzzblx(t.getGzzblx());
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= 业务字段 begin======
			model.setLrr(lrrs[i]);
			// ======= 业务字段 end======
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				fails.add(lrrs[i]);
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
	 * @描述:撤销周报审核
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(XsgzzbshForm model, User user){
		String gzzblx = model.getGzzblx();
		boolean resultXsgzzb = false;
		boolean resultXsgzzbjg = false;
		try {
			//更新申请表
			resultXsgzzb = dao.updateXsgzzbsq(model.getSqid(), Constants.YW_SHZ, gzzblx);
			if(resultXsgzzb){
				//删除结果表
				resultXsgzzbjg = dao.deleteXsgzzbjg(model.getSqid(), gzzblx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultXsgzzbjg;
	}

}
