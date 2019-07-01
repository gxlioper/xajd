/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:38:04 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgService;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:38:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkShService extends SuperServiceImpl<XnwxdkShModel, XnwxdkShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(XnwxdkShModel form, User user) {
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
		model.setZd1(form.getZd1());
		model.setZd3(form.getZd3()+"元");
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("zxdk_xnwxdk_sh.do");
		model.setTzljsq("zxdk_xnwxdk_sq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XnwxdkShModel xnwxdkshform = new XnwxdkShModel();
			xnwxdkshform.setSqid(form.getSqid());
			xnwxdkshform.setShzt(zhzt);
			reuslt = dao.runUpdate(xnwxdkshform, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XnwxdkJgModel xnwxdkForm = new XnwxdkJgModel();
				XnwxdkJgService xnwxdkjgService = new XnwxdkJgService();
				XnwxdkSqModel sqForm = new XnwxdkSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(xnwxdkForm, StringUtils.formatData(sqForm));
//				khjgService.Cjcl(khjgForm);
				
				xnwxdkForm.setXh(form.getXh());
				XyzsglDao gldao = new XyzsglDao();
				Util util = new Util();
				UtilForm utilform = new UtilForm();
				utilform.setXh(sqForm.getXh());
				utilform.setXn(sqForm.getXn());
				utilform.setXq(sqForm.getXq());
				utilform.setType("jg");
				if(!util.isNotExists(utilform)){
					XnwxdkJgDao dao = new XnwxdkJgDao();
					dao.delDkjg(utilform.getXh(),utilform.getXn(),utilform.getXq());
				}
				xnwxdkForm.setJgid(sqForm.getSqid());
				xnwxdkForm.setSjly("1");
				xnwxdkForm.setSqje(form.getZd3());
				reuslt = xnwxdkjgService.runInsert(xnwxdkForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//批量审核
	public String savePlsh(XnwxdkShModel t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XnwxdkShModel model = new XnwxdkShModel();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
			model.setZd1(t.getZd1());
			model.setZd3(t.getZd3());
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
	
	public boolean cancel(XnwxdkShModel myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			XnwxdkJgDao jgdao = new XnwxdkJgDao();
		
			// 删除结果表中的申请结果
			
			jgdao.delDkjgByID(myForm.getSqid());
		
		}
		return result;
	}

	public String cxshnew(String ywid, XnwxdkShModel model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
}
