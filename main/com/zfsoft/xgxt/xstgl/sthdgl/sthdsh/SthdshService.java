/**
 * @部门:学工产品事业部
 * @日期：2015-1-7 下午04:13:52 
 */
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgDao;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgForm;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsq.SthdsqDao;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsq.SthdsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-7 下午04:13:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SthdshService extends SuperServiceImpl<SthdshForm, SthdshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private SthdshDao dao = new SthdshDao();
	private SthdjgDao hdjgDao = new SthdjgDao();

	/**
	 * 
	 * @描述:加载审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-28 上午08:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getHdshInfo(SthdshForm t) {
		return dao.getHdshInfo(t);

	}

	/**
	 * 
	 * @描述:审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-28 上午08:51:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean saveSh(SthdshForm form, User user) {
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
		model.setYwid(form.getHdid());
		model.setSqrid(form.getXh());
		model.setTzlj("stgl_sthdgl_sthdsq.do");
		model.setTzljsq("stgl_sthdgl_sthdsh.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			SthdshForm hdshForm = new SthdshForm();
			hdshForm.setHdid(form.getHdid());
			hdshForm.setShzt(zhzt);
			hdshForm.setYxgs(form.getYxgs());
			reuslt = dao.runUpdate(hdshForm, form.getHdid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				SthdjgForm hdjgForm = new SthdjgForm();
				SthdjgService hdjgService = new SthdjgService();
				SthdsqForm hdsqForm = new SthdsqDao().getModel(form.getHdid());
				BeanUtils.copyProperties(hdjgForm, StringUtils.formatData(hdsqForm));
				hdjgForm.setHdid(hdsqForm.getHdid());
				hdjgForm.setLrr(user.getUserName());
				hdjgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				hdjgForm.setSjly("1");
				reuslt = hdjgService.delByFwsj(hdjgForm);
				if(reuslt)
					reuslt = hdjgService.runInsert(hdjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-28 下午03:22:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return String 返回类型
	 * @throws
	 */
	public String savePlsh(SthdshForm t, User user) throws Exception {
		SthdshForm model = new SthdshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setHdid(ids[i]);
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


	/**
	 * 
	 * @描述:最后一级撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-28 下午03:22:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancel(SthdshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getHdid());
		if (result) {
			hdjgDao.delSthdjgById(myForm.getHdid());
		}
		return result;
	}


}
