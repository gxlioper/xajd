/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 下午05:29:50 
 */
package com.zfsoft.xgxt.axcs.wpsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpjg.WpjgDao;
import com.zfsoft.xgxt.axcs.wpjg.WpjgForm;
import com.zfsoft.xgxt.axcs.wpjg.WpjgService;
import com.zfsoft.xgxt.axcs.wpsq.WpsqDao;
import com.zfsoft.xgxt.axcs.wpsq.WpsqForm;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 下午05:29:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpshService extends SuperServiceImpl<WpshForm, WpshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private WpshDao dao = new WpshDao();

	/**
	 * 
	 * @描述:物品审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午06:43:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean saveSh(WpshForm form, User user) {
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
		model.setTzlj("axcs_axcswpsh.do");
		model.setTzljsq("axcs_axcswpsq_stu.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			WpshForm wpshForm = new WpshForm();
			wpshForm.setSqid(form.getSqid());
			wpshForm.setShzt(zhzt);
			reuslt = dao.runUpdate(wpshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				WpjgForm wpjgForm = new WpjgForm();
				WpjgService wpjgService = new WpjgService();
				WpsqForm wpsqForm = new WpsqDao().getModel(form.getSqid());
				// 根据学号、学年、项目代码删除结果表中数据
				wpjgService.delWpjgxx(wpsqForm.getXh(), wpsqForm.getXn(), wpsqForm.getXmdm());
				BeanUtils.copyProperties(wpjgForm, StringUtils.formatData(wpsqForm));
				wpjgForm.setJgid(wpsqForm.getSqid());
				wpjgForm.setSjly("1");
				reuslt = wpjgService.runInsert(wpjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @描述:审核信息查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-9 上午09:04:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getWpshInfo(WpshForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getWpshInfo(model);
	}

	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-9 上午09:12:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return String 返回类型
	 * @throws
	 */
	public String savePlsh(WpshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			Map<String, String> result = dao.getWpsqInfo(ids[i]);
			WpshForm model = new WpshForm();
			model.setSplc(result.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
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
	 * @日期：2014-12-9 上午09:20:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param ywid
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(WpshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			// 删除结果库中的数据
			WpjgDao jgdao = new WpjgDao();
			jgdao.delWpjgBySqid(myForm.getSqid());
		}
		return result;
	}
}
