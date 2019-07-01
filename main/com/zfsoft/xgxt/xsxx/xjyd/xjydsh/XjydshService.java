package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import xgxt.action.Base;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述:学籍异动审核
 * @作者： qilm
 * @时间：2013-9-29
 * @版本： V1.0
 */
public class XjydshService extends SuperServiceImpl<XjydshForm, XjydshDAO> {
	/**
	 * 数据来源：1[审批流]
	 */
	private static final String SJLY_SPL = "1";
	
	private XjydshDAO dao = new XjydshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XjydshService() {
		super.setDao(dao);
	}
	/**
	 * @描述:学籍异动审核
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean ydsh(XjydshForm form,User user) throws Exception{
		
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		// 退回岗位
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getXjydsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xjyd_xjydsh.do");
		model.setTzljsq("xjyd_xjydsq.do");
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XjydshForm upForm = new XjydshForm();
			upForm.setShzt(zhzt);
			result = dao.runUpdate(upForm, form.getXjydsqid());
			
			// 最后一级审核通过时
			if(result && zhzt.equals(Constants.YW_TG)){
				
				// 插入学籍异动结果库
				result = insertYdjg(form);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:插入学籍异动结果库
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	private boolean insertYdjg(XjydshForm form) throws Exception{
		
		boolean bolFlg = false;
		XjydjgForm ydjgForm = new XjydjgForm();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		XjydjgService xjydjgService = new XjydjgService();
		XjydsqService xjydsqService = new XjydsqService();
		
		// 异动审核
		XjydsqForm  ydsqForm = xjydsqService.getModel(form.getXjydsqid());
		String guid = UniqID.getInstance().getUniqIDHash();
		ydjgForm.setXjydjgid(guid);
		ydjgForm.setXh(ydsqForm.getXh());
		ydjgForm.setJrsj(date);
		ydjgForm.setXn(ydsqForm.getXn());
		ydjgForm.setXq(ydsqForm.getXq());
		ydjgForm.setYdlbdm(ydsqForm.getYdlbdm());
		ydjgForm.setFilepath(ydsqForm.getFilepath());
		
		// 异动前
		ydjgForm.setYdqnj(ydsqForm.getYdqnj());
		ydjgForm.setYdqxydm(ydsqForm.getYdqxydm());
		ydjgForm.setYdqzydm(ydsqForm.getYdqzydm());
		ydjgForm.setYdqbjdm(ydsqForm.getYdqbjdm());
		ydjgForm.setYdqxjlb(ydsqForm.getYdqxjlb());
		ydjgForm.setYdqxjlbmc(ydsqForm.getYdqxjlbmc());
		ydjgForm.setYdqsfyxjmc(ydsqForm.getYdqsfyxjmc());
		ydjgForm.setYdqsfzxmc(ydsqForm.getYdqsfzxmc());
		
		// 异动后(审核后设定)
		ydjgForm.setYdhnj(form.getYdhnj());
		ydjgForm.setYdhxydm(form.getYdhxydm());
		ydjgForm.setYdhzydm(form.getYdhzydm());
		ydjgForm.setYdhbjdm(form.getYdhbjdm());
		ydjgForm.setYdhxjlb(ydsqForm.getYdlbdm());
		
		// 华中师范个性化字段
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			ydjgForm.setXz(form.getXz());
			ydjgForm.setYdyydm(form.getYdyydm());
			ydjgForm.setSfsfs(form.getSfsfs());
			ydjgForm.setLyqxxxdm(form.getLyqxxxdm());
			ydjgForm.setDqztdm(form.getDqztdm());
		}
		
		// 设定数据来源
		ydjgForm.setSjly(SJLY_SPL);					
		ydjgForm.setXjydsqid(ydsqForm.getXjydsqid());

		// 申请理由
		ydjgForm.setSqly(ydsqForm.getSqly());
		ydjgForm.setSqr(ydsqForm.getSqr());
		
		// 文号时间备注
		ydjgForm.setXjydwh(form.getXjydwh());
		ydjgForm.setXjydsj(form.getXjydsj());
		ydjgForm.setXjydbz(form.getXjydbz());
		
		// 申请起止时间
		ydjgForm.setSqkssj(form.getSqkssj());
		ydjgForm.setSqjssj(form.getSqjssj());
		
		String xjydjgidTemp = xjydjgService.queryExistId(ydjgForm, "add");
		if (!"".equals(xjydjgidTemp)){
			ydjgForm.setXjydjgid(xjydjgidTemp);
			bolFlg = xjydjgService.runUpdate(ydjgForm);
		}else{
			bolFlg = xjydjgService.runInsert(ydjgForm);
		}
		if(bolFlg){
			// 更新学生信息
			bolFlg = xjydjgService.updateXsxx(ydjgForm);
		}
		
		return bolFlg;
	}
	
	/** 
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param shlc 审核流程ID
	 * @param ssydsqid 申请ID
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(String shlc, String xjydsqid) throws Exception{
		
		XjydjgService ydjgService = new XjydjgService();
		
		// 更新申请表的审核状态
		XjydshForm upForm = new XjydshForm();
		upForm.setXjydsqid(xjydsqid);
		// 更新前，获取申请记录的审核状态
		String oldShzt = getModel(upForm).getShzt();
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, xjydsqid);
		
		if(result && Constants.YW_TG.equals(oldShzt)){

			XjydjgForm myForm = ydjgService.getModelBySqid(xjydsqid);
			// 回滚学生信息
			result = ydjgService.rollBackXsxx(myForm.getXjydjgid());
			if(result){
				
				// 删除该申请对应的学籍异动结果库
				int count = ydjgService.runDeleteYdjg(xjydsqid);
				if(count > 0 ){
				}else{
					result = false;
				}
			}
		}
		return result;
		
	}

	/**
	 * 
	 * @描述:是否是最后一级岗位
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-11 下午03:35:05
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isZhgw(String gwid ,String splc) {
		ArrayList<HashMap<String, String>> spgws = dao.getSplcgw(splc);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return gwid.equalsIgnoreCase(spgw);
	}
}
