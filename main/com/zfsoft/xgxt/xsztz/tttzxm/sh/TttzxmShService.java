/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:46:06 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.xsztz.tttzxm.jg.TttzxmJgForm;
import com.zfsoft.xgxt.xsztz.tttzxm.jg.TttzxmJgService;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgDao;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.tzxmsh.XsXmShForm;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqDao;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:46:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmShService extends SuperServiceImpl<TttzxmShForm, TttzxmShDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述:审核保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 上午10:57:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String saveSh(TttzxmShForm form, User user) throws Exception {
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
		model.setYwid(form.getTtsqid());
		model.setSqrid(form.getSqr());
		model.setTzlj("sztz_ttxm_sh.do");
		model.setTzljsq("sztz_ttxm_sq.do");
		boolean reuslt = false;
		String flag = "false";
		try {
			//判断流程序号
			HashMap<String,String> shxx =ShlcDao.getTttzxmByCondition(form.getTtsqid(), user.getUserName(),form.getSplc(), "sh");
			//审核前一步骤的项目代码
			String rskzxh = dao.getRskzXh(form.getXmdm());
			
			//如果审核通过，并且审核级别大于等于控制级别，更新调整后项目代码
			if(Constants.SH_TG.equals(form.getShjg()) && (shxx.get("xh")!=null)
					&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
				 checkRskz(form.getGwid(),form.getXmdm());
				
			}
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = dao.runUpdate(form, form.getTtsqid());
			if(reuslt){
				flag = "true";
			}
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				TttzxmJgForm jgForm = new TttzxmJgForm();
				TttzxmJgService jgService = new TttzxmJgService();
				form = dao.getModel(form);
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(form));
				jgForm.setTtjgid(form.getTtsqid());
//				String ids = dao.checkExistForSave2(xsxmsqForm.getXh(),xsxmsqForm.getXq(),xsxmsqForm.getXn(),xsxmsqForm.getXmdm());
//				if(!ids.equals("") && ids != null){
//					xmjgService.runDelete(new String[]{ids});
//				}
				jgForm.setSjly("1");
				reuslt = jgService.runInsert(jgForm);
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
	
	/**
	 * 
	 * @描述: 检测人数
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:33:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwid
	 * @param xmdm
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void checkRskz(String gwid, String xmdm)
			throws Exception {
		Map<String, String> rsszMap = dao.getDataById(xmdm);
		String xzrs = rsszMap.get("kcyrs");
		// 未设置就不控制
		if (StringUtil.isNull(xzrs)) {
			return;
		}
		String tgrs = dao.getTgrs(gwid, xmdm);
		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
		}
	}
	
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:33:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(TttzxmShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		TttzxmShForm model = new TttzxmShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] splcs = t.getSplcs();
		String[] xmdms = t.getXmdms();
		String[] sqrs = t.getSqrs();
		List<String> failXhs = new ArrayList<String>();
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setTtsqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			//model.setDzxh(xhs[i]);
			model.setXmdm(xmdms[i]);
			model.setSqr(sqrs[i]);
			String isSuccess = saveSh(model, user);
			if (!isSuccess.equals("true") && !isSuccess.equals("false")) {
				return isSuccess;
			}
			
			if (!isSuccess.equals("true") && isSuccess.equals("false")) {
				failXhs.add(sqrs[i]);
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
	 * @描述: 最后一级撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:34:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(TttzxmShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getTtsqid());
		if (result) {
			new TttzxmJgService().runDelete(new String[]{myForm.getTtsqid()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:41:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, TttzxmShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setTtsqid(ywid);
		model.setShzt(shzt);
		dao.runUpdate(model);
		return cancelFlag;

	}

}
