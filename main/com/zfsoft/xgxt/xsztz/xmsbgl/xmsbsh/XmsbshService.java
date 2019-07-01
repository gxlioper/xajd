/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:46 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbsh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-7-13下午02:38:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsbshService extends SuperServiceImpl<XmsbshForm, XmsbshDao>{
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private ShlcInterface shlc = new CommShlcImpl();
	private XmsbshDao dao = new XmsbshDao();
	/**
	 * 
	 * @描述:加载审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 上午10:40:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String xmdm) {
		return dao.getXmshInfo(xmdm);

	}
	/**
	 * 
	 * @描述:审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午02:35:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(XmsbshForm form, User user) {
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
		model.setYwid(form.getXmdm());
		model.setSqrid(user.getUserName());
		model.setTzlj("sztz_xmsbgl_xmsh.do");
		model.setTzljsq("sztz_xmsbgl_xmsb.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XmsbshForm sbshForm = new XmsbshForm();
			sbshForm.setXmdm(form.getXmdm());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getXmdm());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XmsbjgForm xmsbjgForm = new XmsbjgForm();
				XmsbjgService xmsbjgService = new XmsbjgService();
				XmsbForm xmsbForm = new XmsbDao().getModel(form.getXmdm());
				BeanUtils.copyProperties(xmsbjgForm, StringUtils.formatData(xmsbForm));
				xmsbjgForm.setJgid(xmsbForm.getXmdm());
				xmsbjgForm.setSjly("1");
				xmsbjgService.runInsert(xmsbjgForm);
				
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
	 * @日期：2015-7-13 下午02:35:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(XmsbshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			Map<String, String> result = dao.getXmshInfo(ids[i]);
			XmsbshForm model = new XmsbshForm();
			model.setSplc(result.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setXmdm(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
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
	 * @日期：2015-7-13 下午01:59:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(XmsbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getXmdm());
			// 删除结果库中的数据
			XmsbjgDao xmsbjgDao = new XmsbjgDao();
			result = xmsbjgDao.delXmsbjg(myForm.getXmdm());
		return result;
	}



}
