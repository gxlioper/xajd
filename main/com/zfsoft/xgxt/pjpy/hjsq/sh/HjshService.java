package com.zfsoft.xgxt.pjpy.hjsq.sh;

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
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgDao;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgForm;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgService;

public class HjshService extends SuperServiceImpl<HjshForm, HjshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 保存审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-12 下午04:07:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(HjshForm form, User user) {
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
//		model.setZd1("有效工时");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("pjpy_jxsh.do");
		model.setTzljsq("pjpy_jxsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = dao.runUpdate(form, form.getId());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HjshForm shForm = dao.getModel(form.getId());
				dao.deleteJg(shForm.getXn(),shForm.getXq(),shForm.getXh(),shForm.getHjmc());
				HjjgForm jgForm = new HjjgForm();
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
				jgForm.setSjly("1");
				reuslt = new HjjgService().runInsert(jgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-12 下午04:25:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(HjshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		HjshForm model = new HjshForm();
		String[] sqids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setId(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
			model.setSplc(splcs[i]);
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
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-12 下午04:56:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(HjshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getId());
		if (result) {
			HjjgDao jgdao = new HjjgDao();
		
			// 删除结果表中的申请结果
			
			jgdao.runDelete(new String[]{myForm.getId()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-12 下午04:55:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, HjshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		model.setId(ywid);
		model.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.runUpdate(model, ywid);
		return cancelFlag;

	}
	
}
