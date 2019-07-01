package com.zfsoft.xgxt.rcsw.ylbx.ylbxsh;

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
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgDao;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgForm;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxsq.YlbxsqForm;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;

public class YlbxshService extends SuperServiceImpl<YlbxshForm, YlbxshDao> {

	private YlbxshDao dao = new YlbxshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YlbxshService() {
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
	public boolean saveSh(YlbxshForm form,User user){
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
		model.setSqrid(form.getXh());
		// ======= 业务字段 end======
		model.setTzlj("rcsw_ylbx_ylbxsh.do");
		model.setTzljsq("rcsw_ylbx_ylbxsq.do");
		if("13573".equals(Base.xxdm)) {
			if(form.getShjg().equals("1")) {
				// 設定业务字段1[业务名称]
				model.setZd1("医保号");
				// 設定业务字段2[业务ID]
				model.setZd2(form.getZd30()); // 医保号
				model.setZd3(form.getZd30()); // 医保号		
			}
		}
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			YlbxshForm upForm = new YlbxshForm();
			upForm.setSqid(form.getSqid());
			if("13573".equals(Base.xxdm)) {
				if(zhzt.equals(Constants.SH_TG)) {
					upForm.setZd30(form.getZd30());
				}
			}
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				YlbxjgForm ylbxjgForm = new YlbxjgForm();
				form = getModel(form);
				if("13573".equals(Base.xxdm)) {
					form.setZd30(form.getZd30());
				}
        		BeanUtils.copyProperties(ylbxjgForm, StringUtils.formatData(form));
        		ylbxjgForm.setSjly("1");
        		YlbxjgDao ylbxjgDao = new YlbxjgDao();
        		ylbxjgDao.runInsert(ylbxjgForm);	
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
	public String savePlsh(YlbxshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= 业务字段 begin======
		String[] xhs = t.getXhs();
		// ======= 业务字段 end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			YlbxshForm model = new YlbxshForm();
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= 业务字段 begin======
			model.setXh(xhs[i]);
			// ======= 业务字段 end======
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				fails.add(xhs[i]);
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
	 * @描述:撤销医疗保险审核
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(YlbxshForm model, User user){
		boolean resultYlbx = false;
		try {
			model = getModel(model);
			//更新申请表
			resultYlbx = dao.updateYlbxsq(model.getSqid(), Constants.YW_SHZ);
			if(resultYlbx && Constants.YW_TG.equals(model.getShzt())){
				//删除结果表
				resultYlbx = dao.deleteYlbxjg(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultYlbx;
	}

	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxshForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}
	
	/**
	 * 
	 * @描述:取最新审核状态数据
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-5 下午03:06:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getLastShxx(String sqid) {		
		return dao.getLastShxx(sqid);	
	}
	
	/**
	 * 
	 * @描述: 审核过程判断结果库中有无数据
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-6 下午01:23:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(String xh, String xn) {	
		return dao.checkExistForSave(xh, xn);
	}
}
