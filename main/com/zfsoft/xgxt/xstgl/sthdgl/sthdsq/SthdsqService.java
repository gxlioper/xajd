/**
 * @部门:学工产品事业部
 * @日期：2015-7-27 下午05:04:19 
 */
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-27 下午05:04:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SthdsqService extends SuperServiceImpl<SthdsqForm, SthdsqDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	SthdsqDao dao = new SthdsqDao();

	/**
	 * 
	 * @描述:考核申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午02:26:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveSthdsq(SthdsqForm model) throws Exception {
		String hdid = UniqID.getInstance().getUniqIDHash();
		model.setHdid(hdid);
		String splc = dao.getShlcID();
		model.setSplc(splc);
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
				result = shlc.runSubmit(hdid, splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @描述:社团活动申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午02:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveEditSthdsq( SthdsqForm model) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getHdid(), splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
		} else {
			result = runUpdate(model);
		}
		return result;
	
	}
	/**
	 * 
	 * @描述:社团活动申请提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-27 下午03:17:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitSthdsq(SthdsqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getHdid(), splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
			return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午03:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(SthdsqForm model) throws Exception {
		return dao.isHaveSqJl(model);
	}
    /**
     * 
     * @描述:初始化下拉列表
     * @作者：夏夏[工号：1104]
     * @日期：2015-7-27 上午10:59:11
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param request
     * @param user
     * @throws Exception
     * void 返回类型 
     * @throws
     */
	public void initParam(HttpServletRequest request,User user) throws Exception{
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sthdList", dao.getStxmList());
		
	}
	
	public SthdsqForm getSqxx(SthdsqForm model) throws Exception{
		return dao.getSqxx(model);
	}
	

}
