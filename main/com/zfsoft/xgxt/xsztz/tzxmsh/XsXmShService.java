/**
 * @部门:学工产品事业部
 * @日期：2015-7-20 下午03:55:42 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgDao;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqDao;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;
import com.zfsoft.xgxt.xsztz.xwtzxmjg.XwTzXmJgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-20 下午03:55:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmShService extends SuperServiceImpl<XsXmShForm, XsXmShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *审核保存
	 */
	public String saveSh(XsXmShForm form, User user) throws Exception {
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
		model.setTzlj("sztz_xmsqgl_xmsh.do");
		model.setTzljsq("sztz_xmsqgl_xmsq.do");
		boolean reuslt = false;
		String flag = "false";
		try {
			//判断流程序号
			HashMap<String,String> shxx =ShlcDao.getXstzxmByCondition(form.getSqid(), user.getUserName(),form.getSplc(), "sh");
			//审核前一步骤的项目代码
			String tzhxmdm = "";
			String rskzxh = dao.getRskzXh(form.getXmdm());
			
			//如果审核通过，并且审核级别大于等于控制级别，更新调整后项目代码
			if(Constants.SH_TG.equals(form.getShjg()) && (shxx.get("xh")!=null)
					&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
				 checkRskz(form.getGwid(),form.getXmdm(),form.getXh(),"sh");
				
			}
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = dao.runUpdate(form, form.getSqid());
			if(reuslt){
				flag = "true";
			}
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XsXmJgForm xmjgForm = new XsXmJgForm();
				XsXmJgService xmjgService = new XsXmJgService();
				XsXmSqDao xsxmsqdao = new XsXmSqDao();
				XsXmSqForm xsxmsqForm = xsxmsqdao.getModel(form.getSqid());
				BeanUtils.copyProperties(xmjgForm, StringUtils.formatData(xsxmsqForm));
				xmjgForm.setJgid(xsxmsqForm.getSqid());
				String ids = dao.checkExistForSave2(xsxmsqForm.getXh(),xsxmsqForm.getXq(),xsxmsqForm.getXn(),xsxmsqForm.getXmdm());
				if(!ids.equals("") && ids != null){
					xmjgService.runDelete(new String[]{ids});
				}
				xmjgForm.setSjly("1");
				reuslt = xmjgService.runInsert(xmjgForm);
				if(reuslt){
					flag ="true";
				}else{
					flag ="false";
				}
			}
		} catch (Exception e) {
			System.out.println("错误信息:"+e.getMessage());
			return e.getMessage();
		}
		return flag;
	}
	
	// 检测人数
	private void checkRskz(String gwid, String xmdm, String xh, String type)
			throws Exception {
		Map<String, String> rsszMap = dao.getDataById(xmdm);
		String xzrs = rsszMap.get("kcyrs");
		// 未设置就不控制
		if (StringUtil.isNull(xzrs)) {
			return;
		}
		String tgrs = dao.getTgrs(gwid, xmdm,Base.currXn, Base.currXq);
		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
		}
	}
	
	//批量审核
	public String savePlsh(XsXmShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XsXmShForm model = new XsXmShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] xmdms = t.getXmdms();
		String[] xns = t.getXns();
		String[] xqs = t.getXqs();
		List<String> failXhs = new ArrayList<String>();
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(splcs[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXmdm(xmdms[i]);
			model.setXn(xns[i]);
			model.setXq(xqs[i]);
			String isSuccess = saveSh(model, user);
			if (!isSuccess.equals("true") && !isSuccess.equals("false")) {
				return isSuccess;
			}
			
			if (!isSuccess.equals("true") && isSuccess.equals("false")) {
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
	
	public boolean cancel(XsXmShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			XsXmJgDao jgdao = new XsXmJgDao();
			jgdao.runDelete(new String[]{myForm.getSqid()});
			
		
		}
		return result;
	}

	public String cxshnew(String ywid, XsXmShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}

}
