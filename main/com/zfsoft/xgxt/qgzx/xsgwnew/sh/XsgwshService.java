/**
 * @部门:学工产品事业部
 * @日期：2016-6-6 上午09:34:55 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqDao;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqForm;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位审核  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-6 上午09:34:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwshService extends SuperServiceImpl<XsgwshnewForm, XsgwshDao>{
	
	private XsgwshDao dao = new XsgwshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 标志没有通过 需验证权限审核
	 */
	private final String _WTGSJ = "-1";
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-8 上午08:23:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shr
	 * @param shid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cxRollBack(String shr, String shid) throws Exception {
		boolean isOk = true;
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		XsgwshnewForm xf = getModel(ywid);
		if (cxsqgw(shr, xf, xf.getSqbh(), xf.getGwdm(), gwid)) {
			isOk = dao.runUpdate(xf);
		}
		return isOk;
	}
	
	public boolean cxsqgw(String userid, XsgwshnewForm xf, String sqbh,
			String ywGwdm, String cxGwdm) {
		
		boolean isChange = false;
		HashMap<String, String> oldSpxx = dao.getSjTzShxx(sqbh, ywGwdm);
		if (null == cxGwdm) {
			return true;
		}
		cxGwdm=ShlcUtil.getUpSpgw(xf.getSplc(), cxGwdm); 
		xf.setShgw(cxGwdm);
		isChange = true;		
		if (null != oldSpxx && oldSpxx.size() > 0) {
			xf.setGwdm(oldSpxx.get("zd2"));
			isChange = true;
		}
		return isChange;
	}
	
	/**
	 * 
	 * @描述: 最后一级撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-8 上午08:25:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param sqbh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(String shlc, String sqbh) throws Exception {
		
		XsgwshnewForm model = new XsgwshnewForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.runUpdate(model, sqbh);
		if (result) {
			result = dao.delJgForSq(sqbh);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:25:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(XsgwshnewForm form, User user) {
		
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_xsgwshnew_sh.do");
		model.setTzljsq("qgzx_xsgwsqnew_sq.do");
		model.setZd2(form.getOldgwdm());
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XsgwshnewForm upForm = new XsgwshnewForm();
			upForm.setSqbh(form.getSqbh());
			upForm.setShzt(zhzt);
			
			// 审核通过人数字段操作
			XsgwsqService ws = new XsgwsqService();
			HashMap<String, String> map = ws.getCsszb();
			String splc = form.getSplc();
			String yzgw = map.get("rskzjb");
			// 当前用户非验证岗位 则不进行验证。
			if (ws.isCheck(splc, yzgw, model.getGwid())) {
				// 如果不是不通过且不是退回操作则为正常数据 记录审核id
				// 否则为异常数据
				if (Constants.SH_BTG.equals(model.getShzt())) {
					upForm.setShgw(_WTGSJ);
				} else if (Constants.SH_TH.equals(model.getShzt())) {
					// 是否是退回到验证级别之前，如果是这需要清空
					if (!ws.isCheck(splc, yzgw, model.getThgw())) {
						upForm.setShgw(_WTGSJ);
					} else {
						String spgw = new ShlcDao().getBeforeGwid(splc, model.getThgw());
						upForm.setShgw(spgw);
					}
				} else {
					// 设置为正常审核通过数据
					// upForm.setShgw(getModel(form.getSqbh()).getGwdm());
					upForm.setShgw(form.getGwid());
				}
			}
			// 不通过或者退回时不保存调整后的岗位
			if (!(Constants.SH_BTG.equals(model.getShzt()) || Constants.SH_TH
					.equals(model.getShzt()))) {
				// 重新设置申请岗位，用于岗位调整后操作
				upForm.setGwdm(form.getGwdm());
			}
			// 如果是退回则需要对应退回原申请岗位
			// if (Constants.SH_TH.equals(model.getShzt())) {
			// thsqgw(user.getUserName(),upForm, form.getSplc(), form.getThgw(),
			// form.getSqbh(),
			// form.getGwdm());
			// }
			// 原业务
			reuslt = dao.runUpdate(upForm, form.getSqbh());
			if (zhzt.equals(Constants.OPEN)) {
				HashMap<String, String> xssqxxMap = dao.getXsSqxx(model
						.getYwid());
				reuslt = dao.bcGwxs(xssqxxMap.get("gwdm"), xssqxxMap.get("xh"),
						xssqxxMap.get("sqbh"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述: 验证数据
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:25:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param isTj
	 * @return
	 * @throws SQLException
	 * String 返回类型 
	 * @throws
	 */
	public String yzjb(XsgwshnewForm model, boolean isTj) throws SQLException {
		
		XsgwsqService wdService = new XsgwsqService();
		String shgw = ShlcUtil.getDqGw(model.getSqbh());
		HashMap<String, String> map = wdService.getCsszb();
		String yzgw = map.get("rskzjb");
		String message = wdService.yzjb(model.getXh(), model.getSplc(), model
				.getGwdm(), yzgw, shgw, isTj);
		
		return message;		
	}
	
	/**
	 * 
	 * @描述:审核前验证数据有效性
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:26:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject yzsh(XsgwshnewForm form) throws Exception {
		
		Map<String, String> resultmap = new HashMap<String, String>();
		XsgwsqService wdService = new XsgwsqService();
		XsgwsqForm model = new XsgwsqForm();
		
		model.setGwdm(form.getGwdm());
		model.setXh(form.getXh());
		model.setSplc(form.getSplc());
		
		int xszggwsl = wdService.getXszggwsl(model);
		HashMap<String, String> map = wdService.getCsszb();
		String xsgwsqsplc = map.get("xsgwsqsplc");
		String message = "";
		if (xszggwsl > 0) {
			message = "学生已经在岗，无法审核通过";
		}/*
		 * else if (xszgsl >= Integer.parseInt(xsgws) && Integer.valueOf(xsgws)
		 * != 0) { message = "此学生已经有" + xszgsl + "个岗位，超过学生最大岗位数"; }
		 */
		else if (xsgwsqsplc == null || "".equals(xsgwsqsplc)) {
			message = "还没有定义审批流程不能保存";
		} else {
			// 验证申请的岗位是否已经达到上限
			if (model.getGwdm() != null && !model.getGwdm().equals("")) {
				message = wdService.yzgwxx(model.getGwdm(), form.getXh());
			} else {
				message = "true";
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap);
	}
	
	public String savePlsh(XsgwshnewForm t, User user) throws Exception {
			
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		
		for (int i = 0, n = ids.length; i < n; i++) {
			XsgwshnewForm model = new XsgwshnewForm();
			model.setSqbh(ids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(t.getGwdm());
			model.setSplc(t.getSplc());
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
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
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
		
	}
	
	public String checkTgrs(List<HashMap<String, String>> list)
			throws Exception {
		
		String id = null;
		String gwid = null;
		String message = "true";
		String splc=null;
		
		XsgwsqDao wd = new XsgwsqDao();
		XsgwsqService ws = new XsgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		HashMap<String, Integer> tgsl = new HashMap<String, Integer>();
		String gwmc = null;
		for (HashMap<String, String> hm : list) {
			id = hm.get("id");
			gwid = hm.get("gwid");
			gwmc = hm.get("message");
			splc=hm.get("splc");
			// 暂时和页面耦合，页面提示信息更改这需要对应更改（如果再查询数据库，效率很低）
			gwmc = gwmc.substring(gwmc.indexOf("[") + 1, gwmc.lastIndexOf("]"));
			XsgwshnewForm xf = getModel(id);
			xf.setGwmc(gwmc);
			// 当前用户非验证岗位 则不进行验证。
			String yzgw = map.get("rskzjb");
			HashMap<String, String> gwxx = wd.getGwxx(xf.getGwdm());
			String xqrs = gwxx.get("xqrs");// 需求总人数
			if (!ws.isCheck(xf.getSplc(), yzgw, gwid)) {
				// 记录当前单位岗位审核通过人数+1
				// 不记录。因为如果不是验证级别之后的权限 审核通过不应该计算
				// Integer sl=tgsl.get(xf.getGwdm());
				// sl=null==sl?0:sl;
				// tgsl.put(xf.getGwdm(), sl+1);
				continue;
			} else {
				Integer tgrs = wd.getGwShtgRs(splc,gwid, xf.getGwdm());
				// 获取当前审核通过人数+已审核通过人数 为实际通过人数
				Integer jlrs = tgsl.get(xf.getGwdm());
				jlrs = null == jlrs ? 0 : jlrs;
				tgrs += jlrs;
				if (tgrs > Integer.parseInt(xqrs) && !xqrs.equals("0")) {
					message = "[" + xf.getGwmc() + "]已超岗位所需人数";
					break;
				} else {
					// 记录当前单位岗位审核通过人数+1
					Integer sl = tgsl.get(xf.getGwdm());
					sl = null == sl ? 0 : sl;
					tgsl.put(xf.getGwdm(), sl + 1);
				}
			}
		}
		return message;
	}
	
	
	
}
