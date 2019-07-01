/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpysh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.rcpy.rcpyjg.RcpyjgDao;
import com.zfsoft.xgxt.xsxx.rcpy.rcpyjg.RcpyjgForm;

public class RcpyshService extends SuperServiceImpl<RcpyshForm, RcpyshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public HashMap<String, String> getRcpysqInfo(RcpyshForm model) {
		return dao.getRcpysqInfo(model);
	}

	public boolean saveSh(RcpyshForm form, User user) throws Exception {
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
		model.setTzlj("xsxx_rcpy_rcpysh.do");
		model.setTzljsq("xsxx_rcpy_rcpysq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditing(model);
			RcpyshForm upForm = new RcpyshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的，添加到结果表
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				RcpyjgForm rcpyjgForm = new RcpyjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(rcpyjgForm, StringUtils.formatData(form));
        		rcpyjgForm.setJgid(form.getSqid());
        		rcpyjgForm.setSjly("1");
        		rcpyjgForm.setSqid(form.getSqid());
        		RcpyjgDao jgDao = new RcpyjgDao();
        		jgDao.deleteExist(rcpyjgForm); //需要删除结果表里重复的数据
        		jgDao.runInsert(rcpyjgForm);	
			}	
		return reuslt;
	}

	public boolean newCancelSh(RcpyshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateRcpysq(model.getSqid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.deleteRcpyjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}

	public String savePlsh(RcpyshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			RcpyshForm model = new RcpyshForm();
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
