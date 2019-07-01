/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:40:09 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:40:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlsqService extends SuperServiceImpl<GzjlsqForm, GzjlsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	GzjlsqDao dao = new GzjlsqDao();
	/**
	 * 
	 * @描述:工作记录申请保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:10:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveGzjlsq(GzjlsqForm model) throws Exception {
		
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = dao.getShlcID();
		model.setSqid(sqid);
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getZgh(), "gzjl_gzjlsh.do", "gzjl_gzjlsq.do");
			}
		}
		return result;
	}
	/**
	 * 
	 * @描述:工作记录修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:21:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveEditGzjlsq(HttpServletRequest request, GzjlsqForm model) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType()) || "tj".equalsIgnoreCase(model.getType())) {
			if ("tj".equalsIgnoreCase(model.getType())) {
				String values = request.getParameter("values");
				model.setSqid(values);
				//树人大学个性化需求
				if("11842".equals(Base.xxdm)){
					GzjlsqForm form = dao.getModel(model);
					model.setXh(form.getXh());
				}
			} 
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getZgh(), "gzjl_gzjlsh.do", "gzjl_gzjlsq.do");
			}
		} else {
			
			result = runUpdate(model);
		}
		return result;
	}
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	
	/** 
	 * @描述:六困生
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:47:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLks() {
		return dao.getLksList();
	}
	
	/** 
	 * @描述:增加谈话对象
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:47:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(GzjlsqForm model, User user,HttpServletRequest request) throws Exception {
		String xhArr = request.getParameter("xhArr");
		if("".equals(xhArr)){
			model.setXhArr(new String[]{});
		}else{
		String[] xhs = xhArr.split(",");
		model.setXhArr(xhs);
		}
		return dao.getXsxxList(model, user);
	}
	
	/** 
	 * @描述:得到谈话对象
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:47:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThdxList(String[] xhArr) {
		return dao.getThdxList(xhArr);
		
	}

}
