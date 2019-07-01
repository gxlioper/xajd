/**
 * @部门:学工产品事业部
 * @日期：2015-4-2 上午10:29:31 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsh;

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
import com.zfsoft.xgxt.rcsw.bxgl.bxbxsq.BxbxSqDao;
import com.zfsoft.xgxt.rcsw.bxgl.bxbxsq.BxbxSqForm;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxDao;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxForm;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-4-2 上午10:29:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BxbxshService extends SuperServiceImpl<BxbxshForm,BxbxshDao>{
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private ShlcInterface shlc = new CommShlcImpl();
	private BxbxshDao dao = new BxbxshDao();
	/**
	 * 
	 * @描述:审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-2 下午02:55:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(BxbxshForm form, User user) {
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
		model.setSqrid(user.getUserName());
		model.setTzlj("rcsw_bxgl_bxbxsh.do");
		model.setTzljsq("rcsw_bxgl_bxbxsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			BxbxshForm bxshForm = new BxbxshForm();
			bxshForm.setSqid(form.getSqid());
			bxshForm.setShzt(zhzt);
			result = dao.runUpdate(bxshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XsbxbxForm bxbxForm = new XsbxbxForm();
				XsbxbxService bxbxService = new XsbxbxService();
				BxbxSqForm bxbxsqForm = new BxbxSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(bxbxForm, StringUtils.formatData(bxbxsqForm));
				bxbxForm.setBxid(bxbxsqForm.getSqid());
				bxbxForm.setSjly("1");
				result=bxbxService.runInsert(bxbxForm);
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-2 下午03:32:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(BxbxshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] splcs = t.getSplcs();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BxbxshForm model = new BxbxshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
		
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
	 * @描述:最后一级审核撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-2 下午03:16:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelSh(BxbxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// 删除结果库中的数据
			XsbxbxDao bxbxDao = new XsbxbxDao();
			result = bxbxDao.delBxbxjg(myForm.getSqid());
		return result;
	}

}
